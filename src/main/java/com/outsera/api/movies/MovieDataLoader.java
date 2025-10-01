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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class MovieDataLoader implements CommandLineRunner {

    @Value("classpath:/data/movies.csv")
    private Resource resource;

    @Autowired
    MovieService movieService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public MovieDataLoader() {
    }

    @Override
    public void run(String... args) throws Exception {

        try {

            LOGGER.info("Loading movies into the database from CSV: {}", resource.getFilename());

            movieService.loadMoviesFromCSV(new MultipartFile() {
                @Override
                public String getName() {
                    return resource.getFilename();
                }

                @Override
                public String getOriginalFilename() {
                    return resource.getFilename();
                }

                @Override
                public String getContentType() {
                    return "text/csv";
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public long getSize() {
                    try {
                        return resource.contentLength();
                    } catch (IOException ignore) {
                        return 0;
                    }
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return resource.getInputStream().readAllBytes();
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return resource.getInputStream();
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {
                    resource.getFile().renameTo(dest);
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
