package capgemini.db.persistence;

import capgemini.db.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Commit
    void findById_productExists_success() {
        Product product = new Product();
        product.setName("PC");
        product.setPrice(1000);

        productRepository.saveAndFlush(product);
        assertTrue(product.getId() > 0);

        //TODO Investigate how to invoke SELECT SQL in this case
        Product product2 = productRepository.findById(product.getId()).orElseThrow();
        assertNotNull(product2);
        assertEquals(product.getName(), product2.getName());

    }

 // Test with findByIdSql() method
    @Test
    @Commit
    void findByIdSql_productExists_success() {
        Product product = new Product();
        product.setName("MobSA52");
        product.setPrice(500);

        productRepository.save(product);
        assertTrue(product.getId() > 0);

        //TODO Investigate how to invoke SELECT SQL in this case
        Product product2 = productRepository.findByIdSql(product.getId()).orElseThrow();
        assertNotNull(product2);
        assertEquals(product.getName(), product2.getName());

    }

    @Test
    @Commit
    void findByName_productExists_success() {
        Product product = new Product();
        product.setName("Book");
        product.setPrice(20);

        productRepository.save(product);
        assertTrue(product.getId() > 0);

        Product product2 = productRepository.findByName(product.getName()).orElseThrow();
        assertNotNull(product2);
        assertEquals(product.getId(), product2.getId());
    }

    @Test
    @Commit
    void findByNameAndPrice_productExists_success() {
        Product product = new Product();
        product.setName("Book");
        product.setPrice(20);

        productRepository.save(product);
        assertTrue(product.getId() > 0);

        Product product2 = productRepository.findByNameAndPrice(product.getName(), product.getPrice()).orElseThrow();
        assertNotNull(product2);
        assertEquals(product.getId(), product2.getId());
    }

    @Test
    @Commit
    void search_productExists_success() {
        Product product = new Product();
        product.setName("Book");
        product.setPrice(30);

        productRepository.save(product);
        assertTrue(product.getId() > 0);

        Product product2 = productRepository.search(product.getName(), product.getPrice()).orElseThrow();
        assertNotNull(product2);
        assertEquals(product.getId(), product2.getId());
    }

}