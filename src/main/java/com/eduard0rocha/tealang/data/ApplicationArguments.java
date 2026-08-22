package com.eduard0rocha.tealang.data;

/**
 * Application arguments DTO.
 * 
 * @param filePath the path to a .tea file to run at startup, or {@code null} if none was given
 */
public record ApplicationArguments(String filePath) {

}
