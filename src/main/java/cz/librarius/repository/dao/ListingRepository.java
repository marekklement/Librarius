package cz.librarius.repository.dao;

import java.util.List;

import cz.librarius.domain.Listing;

public interface ListingRepository extends Repository<Listing> {

    List<Listing> findAllByIsbn(Long isbn);

    List<Listing> findAllByInvalidatedIsFalse();

    List<Listing> findByFilter(Double price, String title, String author);

//    @Query(value = "select l from Listing l "
//                   + "join l.book b "
//                   + "join b.authors a "
//                   + "where (:isbn is not null and l.isbn = :isbn) OR "
//                   + "(:title is not null and b.title = :title) OR "
//                   + "(:author is not null and a.name = :author) OR "
//                   + "(:bookCategory is not null and b.bookCategory = :bookCategory) ")
//    List<Listing> findByFilter(@Param("isbn") Long isbn,
//                              @Param("title") String title,
//                              @Param("author") String author,
//                              @Param("bookCategory") String bookCategory);
}