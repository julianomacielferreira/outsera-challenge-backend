/*
 * The MIT License
 *
 * Copyright 2025 juliano.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.outsera.api.movies;

import com.outsera.api.movies.producer.ProducerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Map<String, List<ProducerDTO>> getMovieProducers() {

        List<Movie> winners = movieRepository.findWinnerMovies();

        Map<String, List<Integer>> producerYears = getProducerYears(winners);
        Map<String, List<ProducerDTO>> intervals = calculateIntervals(producerYears);

        return intervals;
    }

    private Map<String, List<Integer>> getProducerYears(List<Movie> winners) {

        Map<String, List<Integer>> producerYears = new HashMap<>();

        for (Movie movie : winners) {

            String[] producers = movie.getProducers().split(",");

            for (String producer : producers) {
                producer = producer.trim();
                producerYears.computeIfAbsent(producer, k -> new ArrayList<>()).add(movie.getYear());
            }
        }

        return producerYears;
    }

    private Map<String, List<ProducerDTO>> calculateIntervals(Map<String, List<Integer>> producerYears) {

        List<ProducerDTO> intervals = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : producerYears.entrySet()) {
            intervals.addAll(calculateProducerIntervals(entry.getKey(), entry.getValue()));
        }

        return getMinAndMaxIntervals(intervals);
    }

    private List<ProducerDTO> calculateProducerIntervals(String producer, List<Integer> years) {

        List<ProducerDTO> intervals = new ArrayList<>();

        if (years.size() > 1) {
            Collections.sort(years);
            for (int i = 0; i < years.size() - 1; i++) {
                int interval = years.get(i + 1) - years.get(i);
                intervals.add(new ProducerDTO(producer, interval, years.get(i), years.get(i + 1)));
            }
        }

        return intervals;
    }

    private Map<String, List<ProducerDTO>> getMinAndMaxIntervals(List<ProducerDTO> intervals) {

        int minInterval = intervals.stream().mapToInt(ProducerDTO::getInterval).min().orElse(Integer.MAX_VALUE);
        int maxInterval = intervals.stream().mapToInt(ProducerDTO::getInterval).max().orElse(0);

        List<ProducerDTO> minIntervals = intervals.stream()
                .filter(interval -> interval.getInterval() == minInterval)
                .collect(Collectors.toList());

        List<ProducerDTO> maxIntervals = intervals.stream()
                .filter(interval -> interval.getInterval() == maxInterval)
                .collect(Collectors.toList());

        Map<String, List<ProducerDTO>> result = new HashMap<>();
        result.put("min", minIntervals);
        result.put("max", maxIntervals);

        return result;
    }
}
