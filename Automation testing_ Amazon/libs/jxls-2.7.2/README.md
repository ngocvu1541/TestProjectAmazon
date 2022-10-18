Jxls 2.7.2
===========

Go to [Jxls Site](http://jxls.sf.net) for the release notes.

Introduction
------------
Jxls is a small Java library to make generation of Excel reports easy.
Jxls uses a special markup in Excel templates to define output formatting and data layout.

Excel generation is required in many Java applications that have some kind of reporting functionality.

Java has great open-source and commercial libraries for creating Excel files (of open source ones worth mentioning are [Apache POI](https://poi.apache.org/)
and [Java Excel API](http://jexcelapi.sourceforge.net/).

Those libraries are quite low-level in a sense that they require you to write a lot of Java code even to create simple Excel files.

Usually you have to manually set each cell formatting and data for the spreadsheet.
Depending on the complexity of the report layout and data formatting the Java code can become quite complex and difficult to debug and maintain.
In addition not all Excel features are supported and can be manipulated with API(for example macros or graphs).
The suggested workaround for unsupported features is to create the object manually in an Excel template  and fill the template with data after that.

Jxls takes this approach to a higher level. All you need to do when working with Jxls is just to define all your report formatting and data layout in an Excel template and run Jxls engine
 providing it with the data to fill in the template. The only code you need to write in the most cases is a simple invocation of Jxls engine with proper configuration.

Features
--------
* XML and binary Excel format output (depends on underlying low-level Java-to-Excel implementation)
* Java collections output by rows and by columns
* Conditional output
* [Expression language](reference/expression_language.html) in report definition markup 
* [Multiple sheets output](reference/multi_sheets.html)
* Native Excel formulas
* Parameterized formulas
* Grouping support
* Merged cells support
* Area listeners to adjust excel generation
* Excel comments mark-up for command definition
* XML mark-up for command definition
* Custom Command definition
* Streaming for fast output and less memory consumption
* Streaming for selected sheets (SelectSheetsForStreamingPoiTransformer)
* Table support
