import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {


    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(1, "The time machine", 450, "Wells");
    Product book2 = new Book(2, "Robin Hood", 300, "Milne");
    Product smartphone1 = new Smartphone(3, "Samsung", 21_999, "Korea");
    Product smartphone2 = new Smartphone(4,"Xiaomi", 18_000, "China");

    @BeforeEach
    public void setup() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
    }


    @Test
    public void shouldRemoveByInvalidId() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(4);
            });
    }

    @Test
    public void shouldSaveByInvalidId() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(book1);
        });
    }

    @Test
    public void shouldRemoveById() {

        repo.removeById(3);

        Product[] expected = {book1, book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldRemoveAllById() {

        repo.removeById(1);
        repo.removeById(2);
        repo.removeById(3);

        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSaveAndFindAll() {
        repo.save(smartphone2);

        Product[] expected = {book1, smartphone1, book2, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }



}
