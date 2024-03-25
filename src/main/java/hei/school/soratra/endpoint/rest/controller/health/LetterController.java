package hei.school.soratra.endpoint.rest.controller.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class LetterController {
    private final Map<String, String> poetryStorage = new HashMap<>();

    @PutMapping("/letter/{id}")
    public ResponseEntity<Void> storePoetry(@PathVariable String id, @RequestBody Map<String, String> requestData) {
        String poetry = requestData.get("poetry");
        poetryStorage.put(id, poetry.toLowerCase());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/letter/{id}")
    public ResponseEntity<Map<String, String>> retrievePoetry(@PathVariable String id) {
        if (!poetryStorage.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        String originalUrl = "https://original.url/" + id;
        String transformedUrl = "https://transformed.url/" + id;

        Map<String, String> responseData = new HashMap<>();
        responseData.put("original_url", originalUrl);
        responseData.put("transformed_url", transformedUrl);
        return ResponseEntity.ok(responseData);
    }

    public static void main(String[] args) {
        SpringApplication.run(LetterController.class, args);
    }
}