package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.data.repository.query.Param;

import java.util.List;

import cz.librarius.domain.Listing;

@Eager
public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findAllByIsbn(Long isbn);

    @Query(value = "select l from Listing l where l.invalidated = FALSE")
    List<Listing> findAllByInvalidatedIsFalse();

    @Query(value = "select l from Listing l "
                   + "join l.book b "
                   + "join b.authors a "
                   + "where (:price is not null and l.price >= :price) AND "
                   + "(:title = '' or b.title = :title) AND "
                   + "(:author = '' or a.name = :author) ")
    List<Listing> findByFilter(@Param("price") Double price,
                              @Param("title") String title,
                              @Param("author") String author);
}