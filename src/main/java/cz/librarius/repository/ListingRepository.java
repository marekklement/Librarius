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
                   + "where (:isbn is not null and l.isbn = :isbn) OR "
                   + "(:title is not null and b.title = :title) OR "
                   + "(:author is not null and a.name = :author) ")
    List<Listing> findByFilter(@Param("isbn") Long isbn,
                              @Param("title") String title,
                              @Param("author") String author);
}