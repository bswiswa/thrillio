package com.semanticsquare.thrillio.constants;

public enum BookGenre {
	  ART("Art"),
	  ADULT("Adult"),
	  BIOGRAPHY("Biography"),
	  CHILDREN("Children"),
	  FICTION("Fiction"),
	  HISTORY("History"),
	  MYSTERY("Mystery"),
	  PHILOSOPHY("Philosophy"),
	  PORN("Porn"),
	  RELIGION("Religion"),
	  ROMANCE("Romance"),
	  SELF_HELP("Self help"),
	  TECHNICAL("Technical");
	
	private String name;
	private BookGenre(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
}
