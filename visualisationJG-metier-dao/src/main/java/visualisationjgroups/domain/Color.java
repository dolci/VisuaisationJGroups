package visualisationjgroups.domain;

import java.util.ArrayList;

import com.google.common.collect.Lists;

public class Color {

	
	
	public static String [] list ={"#17becf","#e377c2",
	"#7f7f7f","#1f77b4",
	"#aec7e8","#ff7f0e",
	"#ffbb78","#2ca02c",
	"#98df8a","#d62728",
	"#ff9896","#9467bd",
	"#c5b0d5","#8c564b",
	"#c49c94","#f7b6d2",
	"#c7c7c7","#bcbd22",
	"#dbdb8d","#9edae5",
     "#393b79","#637939",
     "#bd9e39","#ad494a",
     "#a55194","#ce6dbd",
     "#e6550d","#31a354",
     "#bdbdbd","#fdae6b",
     "#6baed6"};
	public static ArrayList<String> color = Lists.newArrayList(list);
	
	public static ArrayList<String> getColor() {
		return color;
	}
	public static void setColor(ArrayList<String> color) {
		Color.color = color;
	}
	
}
