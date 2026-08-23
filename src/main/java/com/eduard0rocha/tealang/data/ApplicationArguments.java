package com.eduard0rocha.tealang.data;

import java.util.List;

/**
 * Application arguments DTO.
 *
 * @param filePaths the paths to the .tea files to run at startup, or an empty list if none were given
 */
public record ApplicationArguments(List<String> filePaths) {

}
