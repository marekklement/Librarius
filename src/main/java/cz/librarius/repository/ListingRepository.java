package cz.librarius.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import cz.librarius.domain.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findAllByIsbn(String isbn);
}