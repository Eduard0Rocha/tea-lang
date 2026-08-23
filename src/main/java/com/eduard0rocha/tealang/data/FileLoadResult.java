package com.eduard0rocha.tealang.data;

/**
 * Result of a file load operation DTO.
 *
 * @param filePath     the path to the .tea file that was loaded
 * @param success      whether the file was loaded successfully
 * @param errorMessage the error message if loading failed, or {@code null} if it succeeded
 */
public record FileLoadResult(String filePath, boolean success, String errorMessage) {

}
