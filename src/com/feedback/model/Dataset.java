package com.feedback.model;
//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
public class Dataset{
 public String label;
 public String backgroundColor;
 public String borderColor;
 public int borderWidth;
// public List<int> data;
}

//public class Data{
// public List<String> labels;
// public List<Dataset> datasets;
//}
//
//public class Legend{
// public String position;
//}
//
//public class Title{
// public boolean display;
// public String text;
//}
//
//public class Options{
// public boolean responsive;
// public Legend legend;
// public Title title;
//}
//
//public class Root{
// public String type;
// public Data data;
// public Options options;
//}
