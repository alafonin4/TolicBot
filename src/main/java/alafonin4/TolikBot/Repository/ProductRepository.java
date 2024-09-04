package alafonin4.TolikBot.Repository;

import alafonin4.TolikBot.Entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByTitle(String title);

    List<Product> findAllByNameOfProject(String nameOfProject);
    List<Product> findAllByOrderByShopAsc();
}
