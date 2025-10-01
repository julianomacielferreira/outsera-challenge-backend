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
package com.outsera.api;

import com.outsera.api.movies.Movie;
import com.outsera.api.movies.MovieRepository;
import com.outsera.api.movies.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;


    @BeforeEach
    public void setup() {
        movieRepository.deleteAll();

        Movie movie1 = new Movie();
        movie1.setYear(2011);
        movie1.setTitle("Jack and Jill");
        movie1.setStudios("Columbia Pictures");
        movie1.setProducers("Jack Giarraputo and Adam Sandler");
        movie1.setWinner(true);
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setYear(2013);
        movie2.setTitle("Grown Ups 2");
        movie2.setStudios("Columbia Pictures");
        movie2.setProducers("Jack Giarraputo and Adam Sandler");
        movie2.setWinner(true);
        movieRepository.save(movie2);
    }

    @Test
    public void testGetWinnerProducers() {
        Map<String, List<MovieService.ProducerInterval>> result = movieService.getMovieProducers();

        assertNotNull(result);
        assertTrue(result.containsKey("min"));
        assertTrue(result.containsKey("max"));

        List<MovieService.ProducerInterval> minIntervals = result.get("min");
        assertNotNull(minIntervals);
        assertEquals(1, minIntervals.size());

        MovieService.ProducerInterval minInterval = minIntervals.get(0);
        assertEquals("Jack Giarraputo and Adam Sandler", minInterval.getProducer());
        assertEquals(2, minInterval.getInterval());
        assertEquals(2011, minInterval.getPreviousWin());
        assertEquals(2013, minInterval.getFollowingWin());
    }
}
