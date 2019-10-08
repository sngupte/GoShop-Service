package com.lsr.shopit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsr.shopit.entities.DBFile;

public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
