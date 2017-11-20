package com.semanticsquare.thrillio.entities;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.partner.Shareable;

public class Book extends Bookmark implements Shareable {
	private int publicationYear;
	private String publisher;
	private String[] authors;
	private BookGenre genre;
	private double amazonRating;
	private static final long serialVersionUID = 559000082472491874L;

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	public double getAmazonRating() {
		return amazonRating;
	}

	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if(this.getTitle().toLowerCase().contains("porn")) return false;
		if(publisher.toLowerCase().contains("porn")) return false;
		if(genre.toString().toLowerCase().contains("porn")) return false;
		if(genre.toString().toLowerCase().contains("adult")) return false;
		if(genre.toString().toLowerCase().contains("philosophy")) return false;
		if(genre.toString().toLowerCase().contains("self help")) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [publicationYear=" + publicationYear + ", publisher=" + publisher + ", authors="
				+ Arrays.toString(authors) + ", genre=" + genre + ", amazonRating=" + amazonRating + "]";
	}
	
	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
		builder.append("<type>Book</type>");
		builder.append("<title>").append(getTitle()).append("</title>");
		builder.append("<publisher>").append(publisher).append("</publisher>");
		builder.append("<publicationYear>").append(publicationYear).append("</publicationYear>");
		builder.append("<genre>").append(genre).append("</genre>");
		builder.append("<authors>");
//		for(String author: authors) {
//			builder.append(author);
//		}
		
//		Instead of looping through array, we use StringUtils class' from Apache Commons Lang's join function
		builder.append(StringUtils.join(authors, ","));
		builder.append("</authors>");
		builder.append("<amazonRating>").append(amazonRating).append("</amazonRating>");
		builder.append("</item>");
		return builder.toString();
	}
}
