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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Map<String, List<ProducerInterval>> getMovieProducers() {

        List<Movie> winners = movieRepository.findWinnerProducers();

        Map<String, List<Integer>> producerYears = getProducerYears(winners);

        List<ProducerInterval> minIntervals = new ArrayList<>();
        List<ProducerInterval> maxIntervals = new ArrayList<>();

        int minInterval = Integer.MAX_VALUE;
        int maxInterval = 0;

        for (Map.Entry<String, List<Integer>> producerYear : producerYears.entrySet()) {

            List<Integer> years = producerYear.getValue();

            if (years.size() > 1) {

                Collections.sort(years);

                for (int i = 0; i < years.size() - 1; i++) {

                    Integer followingWin = years.get(i + 1);
                    Integer previousWin = years.get(i);

                    int interval = followingWin - previousWin;

                    ProducerInterval producerInterval = new ProducerInterval(producerYear.getKey(), interval, previousWin, followingWin);

                    if (interval < minInterval) {
                        minInterval = interval;
                        minIntervals.clear();
                        minIntervals.add(producerInterval);
                    } else if (interval == minInterval) {
                        minIntervals.add(producerInterval);
                    }

                    if (interval > maxInterval) {
                        maxInterval = interval;
                        maxIntervals.clear();
                        maxIntervals.add(producerInterval);
                    } else if (interval == maxInterval) {
                        maxIntervals.add(producerInterval);
                    }
                }
            }

        }

        Map<String, List<ProducerInterval>> result = new HashMap<>();
        result.put("min", minIntervals);
        result.put("max", maxIntervals);

        return result;
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
}
