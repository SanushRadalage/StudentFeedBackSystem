package com.feedback.model;

import java.util.List;


public class Dataset{
 public String label;
 public String backgroundColor;
 public String borderColor;
 public int borderWidth;
 public List<Integer> data;
}

 class Data{
  List<String> labels;
  List<Dataset> datasets;
}

 class Legend{
  String position;
}

 class Title{
  boolean display;
  String text;
}

 class Options{
  boolean responsive;
  Legend legend;
  Title title;
}

 class Root{
  String type;
  Data data;
  Options options;
}
