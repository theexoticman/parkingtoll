package com.exotic.parkingtoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exotic.parkingtoll.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

}
