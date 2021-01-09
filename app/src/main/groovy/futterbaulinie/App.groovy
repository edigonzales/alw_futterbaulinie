package futterbaulinie

import geoscript.feature.Feature
import geoscript.feature.Field
import geoscript.filter.Filter
import geoscript.geom.Geometry
import geoscript.geom.Point
import geoscript.layer.Format
import geoscript.layer.GeoTIFF
import geoscript.layer.Layer
import geoscript.layer.Raster
import geoscript.layer.Shapefile
import geoscript.workspace.Directory
import geoscript.workspace.GeoPackage
import geoscript.workspace.Workspace
import org.gdal.gdal.TranslateOptions

import java.nio.file.Paths
import java.nio.file.Files

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstJNI;

/*
class App {
    String getGreeting() {
        return 'Hello World!'
    }

    static void main(String[] args) {
        println new App().greeting        
    }
}
*/


def directory = "/Users/stefan/Downloads/"

def tiles = [
    "2594000_1230000_vegetation_uncompressed",
    "2594500_1230000_vegetation_uncompressed",
    "2594000_1230500_vegetation_uncompressed"
    ]


gdal.AllRegister()
gdal.UseExceptions()

println("Running against GDAL " + gdal.VersionInfo())

Dataset dataset = gdal.Open("/vagrant/data/2594500_1230000_vegetation.tif", gdalconstJNI.GA_ReadOnly_get());
Vector<String> optionsVector = new Vector<>();
optionsVector.add("-co");
optionsVector.add("TILED=TRUE");
gdal.Translate("/vagrant/data/fubar2.tif", dataset, new TranslateOptions(optionsVector))

/*
GeoTiffReader reader = new GeoTiffReader(new File("/Users/stefan/Downloads/2594000_1230000_vegetation.tif"));
GeneralEnvelope envelope = reader.getOriginalEnvelope();
reader.dispose();
*/


//File inputFile = new File("/Users/stefan/Downloads/2594000_1230000_vegetation.tif");
//BufferedImage image = javax.imageio.ImageIO.read(inputFile);


//javax.imageio.ImageIO.write(image, "TIFF", new File("/Users/stefan/Downloads/fubar.tif"));

//TiffImageParser tip = new TiffImageParser();
//BufferedImage img = tip.getBufferedImage(new File("/Users/stefan/Downloads/2594000_1230000_vegetation.tif"), null);
//
//System.out.println(img.getColorModel().getColorSpace().getType());

/*
ColorTools colorTools = new ColorTools();
ColorSpace cs = ColorSpace.getInstance(ColorSpace.TYPE_CCLR);
BufferedImage img2 = colorTools.convertToColorSpace(img, cs);
*/




//HashMap params = new HashMap();
//params.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffTagConstants.COMPRESSION_VALUE_UNCOMPRESSED)
//Imaging.writeImage(img2, new File("/Users/stefan/Downloads/fubar.tif"), ImageFormats.TIFF, params);

/*
GridCoverage2D gc = (GridCoverage2D) reader.read(null);


System.out.println(envelope.toString());

GeoTiffWriter writer = new GeoTiffWriter(new File("/Users/stefan/Downloads/fubar.tif"));
writer.write(gc);
*/

//byte[] bytes = Files.readAllBytes(new File("/Users/stefan/Downloads/2594000_1230000_vegetation.tif").toPath());
//InputStream is = new ByteArrayInputStream(bytes);
//BufferedImage newBi = ImageIO.read(is);


//InputStream is = new FileInputStream(new File("/Users/stefan/Downloads/2594000_1230000_vegetation.tif"));
/*
ImageReader reader = ImageIO.getImageReadersByFormatName("tiff").next();
ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(currentContent));
reader.setInput(iis);
//get the first image metadata
TIFFImageMetadata metadata = reader.getImageMetadata(0);
*/

//IIOMetadata m = reader.getImageMetadata(0);
//org.w3c.dom.Node root = m.getAsTree(m.getNativeMetadataFormatName());
//org.w3c.dom.Node n = root.getFirstChild();
//while (n != null ) {
//    System.out.println(n.getNodeName());
//    n = n.getNextSibling();
//}

/*
System.out.println(metadata.getProjection());

GeoTiffIIOMetadataDecoder decoder = new GeoTiffIIOMetadataDecoder(metadata);
System.out.println(decoder.hasGeoKey())

Collection<GeoKeyEntry> geoKeys = decoder.getGeoKeys()

System.out.println(geoKeys.getAt(2).toString());

//display available format names
System.out.println(Arrays.asList(metadata.getMetadataFormatNames()));
*/


/*
for (tile in tiles) {
    println "Processing: " + tile
    
    File file = Paths.get(directory, tile + ".tif").toFile()
    GeoTIFF geotiff = new GeoTIFF(file)
    Raster raster = geotiff.read(tile)
    
    Raster reclassifiedRaster = raster.reclassify([
        [min:-9999, max:-9999, value: 2],
        [min:-9999, max:0,     value: 2],
        [min:0,     max:200,   value: 1]
    ])
    
    File outFile = Paths.get(directory, tile + "_step01.tif").toFile()
    Format outFormat = Format.getFormat(outFile)
    outFormat.write(reclassifiedRaster)
    
    Layer layer = reclassifiedRaster.polygonLayer    
    Workspace geopkg = new GeoPackage(Paths.get(directory, tile + "_step02.gpkg").toFile())
    geopkg.add(layer, tile)
    
    Filter filter = new Filter("(area(the_geom) > 10 AND value = 1) OR (value = 2 AND area(the_geom) < 10)")
    Layer layer3 = layer.filter(new Filter("(area(the_geom) > 10 AND value = 1) OR (value = 2 AND area(the_geom) < 10)"))
    layer3.update(new Field("value", "double"), 1, new Filter("area(the_geom) < 10 AND value = 2"))
    
    Workspace geopkg3 = new GeoPackage(Paths.get(directory, tile + "_step03.gpkg").toFile())
    geopkg3.add(layer3, tile)
    
}
*/
    

//File file = new File("/Users/stefan/Downloads/2594000_1230000_vegetation_uncompressed.tif")
//GeoTIFF geotiff = new GeoTIFF(file)
//Raster raster = geotiff.read("2594000_1230000_vegetation_uncompressed")
//
//// value 0 ist nicht gut, wird als NULL/nodata interpretiert?
//Raster reclassifiedRaster = raster.reclassify([
//    [min:-9999, max:-9999, value: 2],
//    [min:-9999, max:0,     value: 2],
//    [min:0,     max:200,   value: 1]
//])
//
//File outFile = new File("/Users/stefan/Downloads/A_pa1.tif")
//Format outFormat = Format.getFormat(outFile)
//println outFormat.name
//outFormat.write(reclassifiedRaster)
//
//Layer layer = reclassifiedRaster.polygonLayer
//println layer.schema
//println layer.workspace // -> Memory Workspace
//
////Workspace geopkg = new GeoPackage(new File("/Users/stefan/Downloads/A_pa1.gpkg"))
////geopkg.add(layer, "A_pa1")
//
//// Ich verstehe den Filter nicht wirklich. Warum werden nicht bestockte Fläche (value=2) kleiner 10m2 nicht gelöscht,
//// sondern zu bestockten Flächen gemacht?
//// Variante 1
///*
//Layer layer3 = new Layer("A_pa3", layer.schema)
//layer.filter("(area(the_geom) > 10 AND value = 1) OR (value = 2 AND area(the_geom) < 10)").features.each { f -> 
//    Feature feat = new Feature(f.attributes, f.id, f.schema)
//    if (f.get("value") == 2 && f.geom.area < 10) {
//        feat.set("value", 1)
//    } 
//    layer3.add(feat)
//}
//Workspace geopkg3 = new GeoPackage(new File("/Users/stefan/Downloads/A_pa3.gpkg"))
//geopkg3.add(layer3, "A_pa3")
//*/
//
//// Variante 2
//Filter filter = new Filter("(area(the_geom) > 10 AND value = 1) OR (value = 2 AND area(the_geom) < 10)")
//Layer layer3 = layer.filter(filter)
//layer3.update(new Field("value", "double"), 1, new Filter("area(the_geom) < 10 AND value = 2")) 
//
//Workspace geopkg3 = new GeoPackage(new File("/Users/stefan/Downloads/A_pa3_v2.gpkg"))
//geopkg3.add(layer3, "A_pa3_v2")
//
//Geometry g = (Geometry)layer3.getFeatures().get(0).geom
//
//
////Directory workspace = Shapefile.dump(new File("/Users/stefan/Downloads"), layer)
//
////Workspace geopkg = new GeoPackage(new File("/Users/stefan/Downloads/A_pa1.gpkg"))
////geopkg.add(layer, "A_pa1")

println("Hallo Welt.")
