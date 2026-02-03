package com.Hero_Service.Hero_Service.dto;

import java.util.List;

public class PaginationData<T> {
	
	private List<T> items;
	private long totalItems;
	private int currentPageItems;
	private int totalPages;
	
	public PaginationData(List<T> items, int currentPageItems, long totalItems, int totalPages) {
		super();
		this.items = items;
		this.totalItems = totalItems;
		this.currentPageItems = currentPageItems;
		this.totalPages = totalPages;
	}
	
	public PaginationData() {
		super();
	}
	
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public int getCurrentPageItems() {
		return currentPageItems;
	}
	public void setCurrentPageItems(int currentPageItems) {
		this.currentPageItems = currentPageItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
