package Models;

import lombok.Data;

import java.util.List;

@Data
public class BookModel {
    private String userId;
    private List<CollectionOfIsbns> collectionOfIsbns;
    private String isbn;

    @Data
    public static class CollectionOfIsbns {
        private String isbn;
    }
}