pgm-morphological-filters
=========================

A Java visual application intended to work with morphological filters into PGM images. This application uses imageio-pnm to extend the Java image support, in this way .pgm can be handled using Java native code.

This is a simple java application to apply and visualize morphological filters into PGM images.

## Build

```mvn package```

## Run

```java -jar MorphologicalFilters-1.0-SNAPSHOT.jar```

## Usage 

In orther to try the morphological filters two things are necessary: 1 - A .pgm image, 2 - A .ee structuring element*.

Once the application was started File > Open Image. Selected any .pgm image, after that you are automaticaly asked to select a structuring element.

The structuring element uses the following format:

```
EE
RowCount ColumnCount
originX originY
Value Value Value
Value Value Value
```

Example

```
EE
3 3
1 1
* 1 *
1 1 1
* 1 *
```

* Structuring element [definition](http://homepages.inf.ed.ac.uk/rbf/HIPR2/strctel.htm)


After selected the .pgm and the .ee files, just apply a filter and have fun!


## Resources

There are a few .pgm images available at ```/img```, and a couple structuring elements at ```/se```

## Filters 
* Erosion
* Dilation
* Smoothing
* Gradient
* Opening
* Closing
* Laplacian
* Binarization
* Region Count

### Authors ###

* Eugênio Ferreira [eug](https://github.com/eug)
* Guilherme de Oliveira Santos [Srkl](https://github.com/Srkl)

## License

MIT
