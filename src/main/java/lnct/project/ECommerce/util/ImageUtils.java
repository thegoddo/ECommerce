package lnct.project.ECommerce.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Utility class for compressing and decompressing byte arrays,
 * typically used for image data in a database.
 * Uses Deflater and Inflater for ZLIB compression.
 */
public class ImageUtils {

    // Define a buffer size for reading/writing compressed/decompressed data.
    // 4KB is a common and efficient buffer size.
    private static final int BUFFER_SIZE = 4 * 1024; // 4KB

    /**
     * Compresses a byte array using ZLIB compression.
     * This method is useful for reducing the size of binary data (like images)
     * before storing it in a database.
     *
     * @param data The byte array to be compressed.
     * @return A new byte array containing the compressed data, or null if the input data is null
     * or an error occurs during compression.
     */
    public static byte[] compressBytes(byte[] data) {
        // Essential: Check for null input to prevent NullPointerException
        if (data == null) {
            System.err.println("Compression Warning: Input data for compression is null. Returning null.");
            return null;
        }

        Deflater deflater = new Deflater(); // Create a new Deflater (compressor)
        deflater.setInput(data); // Set the input data for the deflater
        deflater.finish(); // Indicate that no more input will be provided

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length); // Initialize output stream
        byte[] buffer = new byte[BUFFER_SIZE]; // Create a buffer for compressed data

        try {
            // Read compressed data into the buffer and write to outputStream
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer); // Compress data into the buffer
                outputStream.write(buffer, 0, count); // Write the compressed bytes to the output stream
            }
            outputStream.close(); // Close the output stream
        } catch (IOException e) {
            // Log any IOException that occurs during writing to the output stream
            System.err.println("Compression Error: IOException during data writing. " + e.getMessage());
            return null; // Return null to indicate compression failure
        } catch (Exception e) {
            // Catch any other unexpected exceptions during compression
            System.err.println("Compression Error: An unexpected error occurred during compression. " + e.getMessage());
            e.printStackTrace(); // Print full stack trace for debugging
            return null;
        } finally {
            deflater.end(); // Release resources held by the deflater
        }

        return outputStream.toByteArray(); // Return the compressed byte array
    }

    /**
     * Decompresses a byte array that was previously compressed using ZLIB.
     * This method is used to retrieve the original binary data (like an image)
     * after fetching it from the database.
     *
     * @param data The byte array to be decompressed (should be compressed data).
     * @return A new byte array containing the decompressed data, or null if the input data is null,
     * empty, or an error occurs during decompression.
     */
    public static byte[] decompressBytes(byte[] data) {
        // Essential: Check for null or empty input to prevent NullPointerException and handle empty data
        if (data == null || data.length == 0) {
            System.err.println("Decompression Warning: Input data for decompression is null or empty. Returning null.");
            return null;
        }

        Inflater inflater = new Inflater(); // Create a new Inflater (decompressor)
        inflater.setInput(data); // Set the input compressed data for the inflater

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length); // Initialize output stream
        byte[] buffer = new byte[BUFFER_SIZE]; // Create a buffer for decompressed data

        try {
            // Read decompressed data into the buffer and write to outputStream
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer); // Decompress data into the buffer
                if (count == 0 && inflater.needsInput()) {
                    // This can happen if the input data is incomplete or malformed.
                    // Prevent infinite loop if no more input can be decompressed.
                    System.err.println("Decompression Error: Inflater needs more input but finished flag is not set. Malformed data?");
                    return null;
                }
                outputStream.write(buffer, 0, count); // Write the decompressed bytes to the output stream
            }
            outputStream.close(); // Close the output stream
        } catch (Exception e) { // Catch all exceptions that can occur during decompression
            // Log the specific error (e.g., DataFormatException for corrupted data)
            System.err.println("Decompression Error: An error occurred during decompression. " + e.getMessage());
            e.printStackTrace(); // Print full stack trace for debugging
            return null; // Return null to indicate decompression failure
        } finally {
            inflater.end(); // Release resources held by the inflater
        }

        return outputStream.toByteArray(); // Return the decompressed byte array
    }
}
