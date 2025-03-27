import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MD5HashGenerator {
    public static void main(String[] args) {
        try {
            // Read JSON file
            String jsonData = readFile("input.json");
            System.out.println("✅ JSON Data Read: " + jsonData); // Debugging output

            // Parse JSON
            JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
            
            // Extract values
            if (!jsonObject.has("first_name") || !jsonObject.has("roll_number")) {
                System.out.println("Error: JSON must contain 'first_name' and 'roll_number'.");
                return;
            }

            String firstName = jsonObject.get("first_name").getAsString().toLowerCase();
            String rollNumber = jsonObject.get("roll_number").getAsString().toLowerCase();
            System.out.println("✅ Extracted first_name: " + firstName);
            System.out.println("✅ Extracted roll_number: " + rollNumber);

            // Concatenate first_name and roll_number
            String inputString = firstName + rollNumber;
            System.out.println("✅ Concatenated String: " + inputString);

            // Generate MD5 hash
            String hash = generateMD5(inputString);
            System.out.println("✅ Generated MD5 Hash: " + hash);

            // Write to output.txt
            writeFile("output.txt", hash);
            System.out.println("✅ MD5 Hash written to output.txt successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read file content
    private static String readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }

    // Write content to file
    private static void writeFile(String filePath, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }

    // Generate MD5 hash
    private static String generateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
