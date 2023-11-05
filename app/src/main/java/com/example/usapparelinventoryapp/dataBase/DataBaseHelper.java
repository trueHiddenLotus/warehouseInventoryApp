package com.example.usapparelinventoryapp.dataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.usapparelinventoryapp.dto.StyleSearchByLocationDTO;
import com.example.usapparelinventoryapp.dto.StyleSearchDTO;
import com.example.usapparelinventoryapp.models.ColorModel;
import com.example.usapparelinventoryapp.models.LocationModel;
import com.example.usapparelinventoryapp.models.PalletModel;
import com.example.usapparelinventoryapp.models.PalletStylesModel;
import com.example.usapparelinventoryapp.models.SizeModel;
import com.example.usapparelinventoryapp.models.StyleModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String size_table = "size_table";
    public static final String size = "size";
    public static final String size_id = "size_id";
    public static final String style_table = "style_table";
    public static final String style_id = "style_id";
    public static final String style_code = "style_code";
    public static final String color_table = "color_table";
    public static final String color_id = "color_id";
    public static final String color = "color";
    public static final String style_color = "style_color";
    public static final String location_table = "location_table";
    public static final String location_id = "location_id";
    public static final String location = "location";
    public static final String pallet_location = "pallet_location";
    public static final String pallet_styles_table = "pallet_styles_table";
    public static final String pallet_styles_id = "pallet_styles_id";
    public static final String style_size = "style_size";
    public static final String style_pallet_id = "style_pallet_id";
    public static final String pallet_table = "pallet_table";
    public static final String pallet_id = "pallet_id";
    public static final String is_removed = "is_removed";
    public static final String quantity = "quantity";
    public static final String saved_pallet_id_table = "saved_pallet_id_table";
    public static final String saved_pallet_id = "saved_pallet_id";

    public static final String saved_pallet = "saved_pallet";
    public static final String CHECK = "CHECK";




    public DataBaseHelper(@Nullable Context context) {
        super(context, "pallets.db", null, 1);
    }

    // this is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {


        String createSizeTableStatement = "CREATE TABLE " + size_table + " ("
                + size_id + " INTEGER NOT NULL, "
                + size + " TEXT PRIMARY KEY NOT NULL)";

        String createStyleTable = "CREATE TABLE " + style_table + " ("
                + style_id + " INTEGER UNIQUE, "
                + style_code + " TEXT PRIMARY KEY NOT NULL)";

        String createColorTable = "CREATE TABLE " + color_table + " ("
                + color_id + " INTEGER NOT NULL, "
                + color  + " TEXT PRIMARY KEY NOT NULL)";

        String createLocationTable = "CREATE TABLE " + location_table + " ("
                + location_id + " INTEGER NOT NULL, "
                + location  + " TEXT PRIMARY KEY NOT NULL)";

        String createPalletTable = "CREATE TABLE " + pallet_table + " ("
                + pallet_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + pallet_location + " TEXT  NOT NULL, "
                + is_removed + " BOOBLEAN DEFAULT (FALSE)  NOT NULL, "
                + CHECK + " (pallet_location <> ''), "
                + " CONSTRAINT " + "location_fk" + " FOREIGN KEY " + "( pallet_location )" + " REFERENCES locaton_table ( location ))";

        String createLocationIdIndex = "CREATE INDEX " + " pallet_location_id_in " + " ON pallet_table" + " ( pallet_location )";

        String createPalletStylesTable = "CREATE TABLE " + pallet_styles_table + " ("
                + pallet_styles_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + style_pallet_id + " INTEGER NOT NULL, "
                + style_code + " TEXT NOT NULL, "
                + style_color + " TEXT NOT NULL, "
                + style_size + " TEXT NOT NULL, "
                + quantity + " INTEGER NOT NULL, "
                + " CONSTRAINT " + "pallet_styles_color_fk" + " FOREIGN KEY " + "( style_color )" + " REFERENCES color_table ( color ), "
                + " CONSTRAINT " + "size_fk" + " FOREIGN KEY " + "( style_size )" + " REFERENCES size_table ( size ), "
                + " CONSTRAINT " + "style_pallet_id" + " FOREIGN KEY " + "( style_pallet_id )" + " REFERENCES pallet_table ( pallet_id ) on delete Cascade, "
                + " CONSTRAINT " + "style_code_fk" + " FOREIGN KEY " + "( style_code )" + " REFERENCES size_table ( style_code ))";

        String createSavedPalletIdTable = "CREATE TABLE " + saved_pallet_id_table + " ("
                + saved_pallet_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + saved_pallet  + " INTEGER NOT NULL)";


        String createColorIdIndex = "CREATE INDEX " + " color_id_in " + " ON pallet_styles_table" + " ( style_color )";
        String createSizeIdIndex = "CREATE INDEX " + " size_id_in " + " ON pallet_styles_table" + " ( style_size)";
        String createStyleIdIndex = "CREATE INDEX " + " style_id_in " + " ON pallet_styles_table" + " ( style_code )";
        String createPalletIdIndex = "CREATE INDEX " + "pallet_id_in " + "ON pallet_styles_table" + " ( style_pallet_id )";

        db.execSQL(createSizeTableStatement);
        db.execSQL(createStyleTable);
        db.execSQL(createColorTable);
        db.execSQL(createLocationTable);
        db.execSQL(createPalletTable);
        db.execSQL(createLocationIdIndex);
        db.execSQL(createPalletStylesTable);
        db.execSQL(createColorIdIndex);
        db.execSQL(createSizeIdIndex);
        db.execSQL(createStyleIdIndex);
        db.execSQL(createPalletIdIndex);
        db.execSQL(createSavedPalletIdTable);

        String sizeInsert1 = "INSERT INTO " + size_table  + " VALUES(1, 'XS' );";
        String sizeInsert2 = "INSERT INTO " + size_table  + " VALUES(2, 'S' );";
        String sizeInsert3 = "INSERT INTO " + size_table  + " VALUES(3, 'M' );";
        String sizeInsert4 = "INSERT INTO " + size_table  + " VALUES(4, 'L' );";
        String sizeInsert5 = "INSERT INTO " + size_table  + " VALUES(5, 'XL' );";
        String sizeInsert6 = "INSERT INTO " + size_table  + " VALUES(6, '2X' );";
        String sizeInsert7 = "INSERT INTO " + size_table  + " VALUES(7, '3X' );";
        String sizeInsert8 = "INSERT INTO " + size_table  + " VALUES(8, '4X' );";


        String colorInsert1 = "INSERT INTO " + color_table  + " VALUES(1, 'AMERICANA' );";
        String colorInsert2 = "INSERT INTO " + color_table  + " VALUES(2, 'ANTHRACITE' );";
        String colorInsert3 = "INSERT INTO " + color_table  + " VALUES(3, 'AQUA' );";
        String colorInsert4 = "INSERT INTO " + color_table  + " VALUES(4, 'AQUARIUS' );";
        String colorInsert5 = "INSERT INTO " + color_table  + " VALUES(5, 'ATHLETIC GREY HEATHER' );";
        String colorInsert6 = "INSERT INTO " + color_table  + " VALUES(6, 'AUQA SPRINGS' );";
        String colorInsert7 = "INSERT INTO " + color_table  + " VALUES(7, 'BANANA' );";
        String colorInsert8 = "INSERT INTO " + color_table  + " VALUES(8, 'BARN RED' );";
        String colorInsert9 = "INSERT INTO " + color_table  + " VALUES(9, 'BISCAY AQUA' );";
        String colorInsert10 = "INSERT INTO " + color_table  + " VALUES(10, 'BLACK HEATHER' );";
        String colorInsert11 = "INSERT INTO " + color_table  + " VALUES(11, 'BLACK SLATE' );";
        String colorInsert12 = "INSERT INTO " + color_table  + " VALUES(12, 'BLUE' );";
        String colorInsert13 = "INSERT INTO " + color_table  + " VALUES(13, 'BLUE BERRY' );";
        String colorInsert14 = "INSERT INTO " + color_table  + " VALUES(14, 'BLUE CRYSTAL' );";
        String colorInsert15 = "INSERT INTO " + color_table  + " VALUES(15, 'BLUE LAGOON' );";
        String colorInsert16 = "INSERT INTO " + color_table  + " VALUES(16, 'BLUE/GREEN' );";
        String colorInsert17 = "INSERT INTO " + color_table  + " VALUES(17, 'BLUESKIES/H.GREY' );";
        String colorInsert18 = "INSERT INTO " + color_table  + " VALUES(18, 'BUTTERCUP' );";
        String colorInsert19 = "INSERT INTO " + color_table  + " VALUES(19, 'CALIENTE' );";
        String colorInsert20 = "INSERT INTO " + color_table  + " VALUES(20, 'CAMEL' );";
        String colorInsert21 = "INSERT INTO " + color_table  + " VALUES(21, 'CANDY PINK' );";
        String colorInsert22 = "INSERT INTO " + color_table  + " VALUES(22, 'CANTALOPE' );";
        String colorInsert23 = "INSERT INTO " + color_table  + " VALUES(23, 'CANYON RED' );";
        String colorInsert24 = "INSERT INTO " + color_table  + " VALUES(24, 'CARIBE' );";
        String colorInsert25 = "INSERT INTO " + color_table  + " VALUES(25, 'CHARCOAL' );";
        String colorInsert26 = "INSERT INTO " + color_table  + " VALUES(26, 'CHARCOAL/CREAM' );";
        String colorInsert27 = "INSERT INTO " + color_table  + " VALUES(27, 'CITRUS PUNCH' );";
        String colorInsert28 = "INSERT INTO " + color_table  + " VALUES(28, 'CLASSIC RED' );";
        String colorInsert29 = "INSERT INTO " + color_table  + " VALUES(29, 'COASTAL' );";
        String colorInsert30 = "INSERT INTO " + color_table  + " VALUES(30, 'COBALT' );";
        String colorInsert31 = "INSERT INTO " + color_table  + " VALUES(31, 'COCOA' );";
        String colorInsert32 = "INSERT INTO " + color_table  + " VALUES(32, 'CONCH' );";
        String colorInsert33 = "INSERT INTO " + color_table  + " VALUES(33, 'CORAL' );";
        String colorInsert34 = "INSERT INTO " + color_table  + " VALUES(34, 'CORAL REEF' );";
        String colorInsert35 = "INSERT INTO " + color_table  + " VALUES(35, 'COSATA BONITA' );";
        String colorInsert36 = "INSERT INTO " + color_table  + " VALUES(36, 'COSMOS' );";
        String colorInsert37 = "INSERT INTO " + color_table  + " VALUES(37, 'COTTON CANDY' );";
        String colorInsert38 = "INSERT INTO " + color_table  + " VALUES(38, 'CREAMSICLE' );";
        String colorInsert39 = "INSERT INTO " + color_table  + " VALUES(39, 'CRUSH' );";
        String colorInsert40 = "INSERT INTO " + color_table  + " VALUES(40, 'DEMIM' );";
        String colorInsert41 = "INSERT INTO " + color_table  + " VALUES(41, 'DENIM/CREAM' );";
        String colorInsert42 = "INSERT INTO " + color_table  + " VALUES(42, 'DESERT SUN' );";
        String colorInsert43 = "INSERT INTO " + color_table  + " VALUES(43, 'DOLPHIN' );";
        String colorInsert44 = "INSERT INTO " + color_table  + " VALUES(44, 'DUSK' );";
        String colorInsert45 = "INSERT INTO " + color_table  + " VALUES(45, 'DUSTY BLUE' );";
        String colorInsert46 = "INSERT INTO " + color_table  + " VALUES(46, 'DUSTY ROSE' );";
        String colorInsert47 = "INSERT INTO " + color_table  + " VALUES(47, 'ECRU' );";
        String colorInsert48 = "INSERT INTO " + color_table  + " VALUES(48, 'EUCALYPTUS' );";
        String colorInsert49 = "INSERT INTO " + color_table  + " VALUES(49, 'EVERGREEN' );";
        String colorInsert50 = "INSERT INTO " + color_table  + " VALUES(50, 'FADDED PLUM' );";
        String colorInsert51 = "INSERT INTO " + color_table  + " VALUES(51, 'FADED NAVY' );";
        String colorInsert52 = "INSERT INTO " + color_table  + " VALUES(52, 'FLAMINGO' );";
        String colorInsert53 = "INSERT INTO " + color_table  + " VALUES(53, 'FLINT' );";
        String colorInsert54 = "INSERT INTO " + color_table  + " VALUES(54, 'FOSSIL' );";
        String colorInsert55 = "INSERT INTO " + color_table  + " VALUES(55, 'FOSTED GARPE' );";
        String colorInsert56 = "INSERT INTO " + color_table  + " VALUES(56, 'GALAXY' );";
        String colorInsert57 = "INSERT INTO " + color_table  + " VALUES(57, 'GRAPEFRUIT' );";
        String colorInsert58 = "INSERT INTO " + color_table  + " VALUES(58, 'GREY HEATHER' );";
        String colorInsert59 = "INSERT INTO " + color_table  + " VALUES(59, 'GREY STONE' );";
        String colorInsert60 = "INSERT INTO " + color_table  + " VALUES(60, 'GREY/CHARCOAL' );";
        String colorInsert61 = "INSERT INTO " + color_table  + " VALUES(61, 'GUNMETAL' );";
        String colorInsert62 = "INSERT INTO " + color_table  + " VALUES(62, 'HEATHER ASH/HEATHER NAVY' );";
        String colorInsert63 = "INSERT INTO " + color_table  + " VALUES(63, 'HEATHER GREY/BLACK' );";
        String colorInsert64 = "INSERT INTO " + color_table  + " VALUES(64, 'HEATHER GREY/H.CHARCOAL' );";
        String colorInsert65 = "INSERT INTO " + color_table  + " VALUES(65, 'HOT AQUA' );";
        String colorInsert66 = "INSERT INTO " + color_table  + " VALUES(66, 'HOT PINK' );";
        String colorInsert67 = "INSERT INTO " + color_table  + " VALUES(67, 'INDIGO' );";
        String colorInsert68 = "INSERT INTO " + color_table  + " VALUES(68, 'JIUCY' );";
        String colorInsert69 = "INSERT INTO " + color_table  + " VALUES(69, 'KAUAI' );";
        String colorInsert70 = "INSERT INTO " + color_table  + " VALUES(70, 'LAGOON' );";
        String colorInsert71 = "INSERT INTO " + color_table  + " VALUES(71, 'LIGHT BLUE' );";
        String colorInsert72 = "INSERT INTO " + color_table  + " VALUES(72, 'LIGHT PINK' );";
        String colorInsert73 = "INSERT INTO " + color_table  + " VALUES(73, 'LODEN' );";
        String colorInsert74 = "INSERT INTO " + color_table  + " VALUES(74, 'MAGICAL' );";
        String colorInsert75 = "INSERT INTO " + color_table  + " VALUES(75, 'MARLIN BLUE' );";
        String colorInsert76 = "INSERT INTO " + color_table  + " VALUES(76, 'MAROON' );";
        String colorInsert77 = "INSERT INTO " + color_table  + " VALUES(77, 'MAROON/H.CHARCOAL' );";
        String colorInsert78 = "INSERT INTO " + color_table  + " VALUES(78, 'MELLOW YELLOW' );";
        String colorInsert79 = "INSERT INTO " + color_table  + " VALUES(79, 'MERMAID' );";
        String colorInsert80 = "INSERT INTO " + color_table  + " VALUES(80, 'MIDNIGHT' );";
        String colorInsert81 = "INSERT INTO " + color_table  + " VALUES(81, 'MILITARY GREEN' );";
        String colorInsert82 = "INSERT INTO " + color_table  + " VALUES(82, 'MINERAL WASH' );";
        String colorInsert83 = "INSERT INTO " + color_table  + " VALUES(83, 'MINT' );";
        String colorInsert84 = "INSERT INTO " + color_table  + " VALUES(84, 'MOONLIGHT' );";
        String colorInsert85 = "INSERT INTO " + color_table  + " VALUES(85, 'MOSS' );";
        String colorInsert86 = "INSERT INTO " + color_table  + " VALUES(86, 'MULTI' );";
        String colorInsert87 = "INSERT INTO " + color_table  + " VALUES(87, 'NAVY' );";
        String colorInsert88 = "INSERT INTO " + color_table  + " VALUES(88, 'NAVY HEATHER' );";
        String colorInsert89 = "INSERT INTO " + color_table  + " VALUES(89, 'NAVY/DENIM' );";
        String colorInsert90 = "INSERT INTO " + color_table  + " VALUES(90, 'NECTAR' );";
        String colorInsert91 = "INSERT INTO " + color_table  + " VALUES(91, 'OATMEAL' );";
        String colorInsert92 = "INSERT INTO " + color_table  + " VALUES(92, 'OCEANA' );";
        String colorInsert93 = "INSERT INTO " + color_table  + " VALUES(93, 'ONYX' );";
        String colorInsert94 = "INSERT INTO " + color_table  + " VALUES(94, 'PACIFIC BLUE' );";
        String colorInsert95 = "INSERT INTO " + color_table  + " VALUES(95, 'VOID' );";
        String colorInsert96 = "INSERT INTO " + color_table  + " VALUES(96, 'PASTEL SEA' );";
        String colorInsert97 = "INSERT INTO " + color_table  + " VALUES(97, 'PEACH & CREAM' );";
        String colorInsert98 = "INSERT INTO " + color_table  + " VALUES(98, 'PERI HEATHER' );";
        String colorInsert99 = "INSERT INTO " + color_table  + " VALUES(99, 'PERSIAN BLUE' );";
        String colorInsert100 = "INSERT INTO " + color_table  + " VALUES(100, 'PARKRIKA' );";
        String colorInsert101 = "INSERT INTO " + color_table  + " VALUES(101, 'VOIDC' );";
        String colorInsert102 = "INSERT INTO " + color_table  + " VALUES(102, 'VOIDA' );";
        String colorInsert103 = "INSERT INTO " + color_table  + " VALUES(103, 'PINK' );";
        String colorInsert104 = "INSERT INTO " + color_table  + " VALUES(104, 'VOIDB' );";
        String colorInsert105 = "INSERT INTO " + color_table  + " VALUES(105, 'PINK SUGAR' );";
        String colorInsert106 = "INSERT INTO " + color_table  + " VALUES(106, 'POOLSIDE' );";
        String colorInsert107 = "INSERT INTO " + color_table  + " VALUES(107, 'POWDER BLUE' );";
        String colorInsert108 = "INSERT INTO " + color_table  + " VALUES(108, 'PUTTY' );";
        String colorInsert109 = "INSERT INTO " + color_table  + " VALUES(109, 'RASTA' );";
        String colorInsert110 = "INSERT INTO " + color_table  + " VALUES(110, 'RED' );";
        String colorInsert111 = "INSERT INTO " + color_table  + " VALUES(111, 'RIP CURL' );";
        String colorInsert112 = "INSERT INTO " + color_table  + " VALUES(112, 'ROSE' );";
        String colorInsert113 = "INSERT INTO " + color_table  + " VALUES(113, 'ROYAL' );";
        String colorInsert114 = "INSERT INTO " + color_table  + " VALUES(114, 'SAGE' );";
        String colorInsert115 = "INSERT INTO " + color_table  + " VALUES(115, 'SALT & PEPPER' );";
        String colorInsert116 = "INSERT INTO " + color_table  + " VALUES(116, 'SALTY BLUE' );";
        String colorInsert117 = "INSERT INTO " + color_table  + " VALUES(117, 'SALTY PINK' );";
        String colorInsert118 = "INSERT INTO " + color_table  + " VALUES(118, 'SCUBA GREY' );";
        String colorInsert119 = "INSERT INTO " + color_table  + " VALUES(119, 'SEA GLASS' );";
        String colorInsert120 = "INSERT INTO " + color_table  + " VALUES(120, 'SEA GREEN' );";
        String colorInsert121 = "INSERT INTO " + color_table  + " VALUES(121, 'SEA GREEN/HEATHER' );";
        String colorInsert122 = "INSERT INTO " + color_table  + " VALUES(122, 'SEA SHORE' );";
        String colorInsert123 = "INSERT INTO " + color_table  + " VALUES(123, 'SEA SPRAY' );";
        String colorInsert124 = "INSERT INTO " + color_table  + " VALUES(124, 'SEAL' );";
        String colorInsert125 = "INSERT INTO " + color_table  + " VALUES(125, 'SHADOW' );";
        String colorInsert126 = "INSERT INTO " + color_table  + " VALUES(126, 'SHELL' );";
        String colorInsert127 = "INSERT INTO " + color_table  + " VALUES(127, 'SHELL HEATHER' );";
        String colorInsert128 = "INSERT INTO " + color_table  + " VALUES(128, 'SILVER' );";
        String colorInsert129 = "INSERT INTO " + color_table  + " VALUES(129, 'SILVER/PINK' );";
        String colorInsert130 = "INSERT INTO " + color_table  + " VALUES(130, 'SKYBLUE' );";
        String colorInsert131 = "INSERT INTO " + color_table  + " VALUES(131, 'SALT WATER TAFFY' );";
        String colorInsert132 = "INSERT INTO " + color_table  + " VALUES(132, 'SLATE' );";
        String colorInsert133 = "INSERT INTO " + color_table  + " VALUES(133, 'SMOKEY BLUE' );";
        String colorInsert134 = "INSERT INTO " + color_table  + " VALUES(134, 'SPEARMINT' );";
        String colorInsert135 = "INSERT INTO " + color_table  + " VALUES(135, 'STAR FISH' );";
        String colorInsert136 = "INSERT INTO " + color_table  + " VALUES(136, 'STARBURST' );";
        String colorInsert137 = "INSERT INTO " + color_table  + " VALUES(137, 'STEEL' );";
        String colorInsert138 = "INSERT INTO " + color_table  + " VALUES(138, 'SUNFLOWER' );";
        String colorInsert139 = "INSERT INTO " + color_table  + " VALUES(139, 'SUNNY' );";
        String colorInsert140 = "INSERT INTO " + color_table  + " VALUES(140, 'SUNSET' );";
        String colorInsert141 = "INSERT INTO " + color_table  + " VALUES(141, 'TANGO RED' );";
        String colorInsert142 = "INSERT INTO " + color_table  + " VALUES(142, 'TAUPE' );";
        String colorInsert143 = "INSERT INTO " + color_table  + " VALUES(143, 'TEAL/H.GREY' );";
        String colorInsert144 = "INSERT INTO " + color_table  + " VALUES(144, 'THE WAVE' );";
        String colorInsert145 = "INSERT INTO " + color_table  + " VALUES(145, 'TIE-DYE' );";
        String colorInsert146 = "INSERT INTO " + color_table  + " VALUES(146, 'TONE' );";
        String colorInsert147 = "INSERT INTO " + color_table  + " VALUES(147, 'TRADEWINDS' );";
        String colorInsert148 = "INSERT INTO " + color_table  + " VALUES(148, 'TURQOUISE /SHELL' );";
        String colorInsert149 = "INSERT INTO " + color_table  + " VALUES(149, 'TURQUOISE' );";
        String colorInsert150 = "INSERT INTO " + color_table  + " VALUES(150, 'TURTLE BAY' );";
        String colorInsert151 = "INSERT INTO " + color_table  + " VALUES(151, 'TUTTI FRUTTI' );";
        String colorInsert152 = "INSERT INTO " + color_table  + " VALUES(152, 'TWILIGHT' );";
        String colorInsert153 = "INSERT INTO " + color_table  + " VALUES(153, 'ULTRA ORANGE' );";
        String colorInsert154 = "INSERT INTO " + color_table  + " VALUES(154, 'UNICORN' );";
        String colorInsert155 = "INSERT INTO " + color_table  + " VALUES(155, 'VINTAGE BLUE' );";
        String colorInsert156 = "INSERT INTO " + color_table  + " VALUES(156, 'VIOLET' );";
        String colorInsert157 = "INSERT INTO " + color_table  + " VALUES(157, 'WHITE' );";




        String styleInsert1 = "INSERT INTO " + style_table  + " VALUES(1, '19-LS-DEN' );";
        String styleInsert2 = "INSERT INTO " + style_table  + " VALUES(2, '1930' );";
        String styleInsert3 = "INSERT INTO " + style_table  + " VALUES(3, '21-IMP' );";
        String styleInsert4 = "INSERT INTO " + style_table  + " VALUES(4, '21-RES' );";
        String styleInsert5 = "INSERT INTO " + style_table  + " VALUES(5, '21-SWM' );";
        String styleInsert6 = "INSERT INTO " + style_table  + " VALUES(6, '23-LS' );";
        String styleInsert7 = "INSERT INTO " + style_table  + " VALUES(7, 'AFL-9208' );";
        String styleInsert8 = "INSERT INTO " + style_table  + " VALUES(8, 'AFL-92' );";
        String styleInsert9 = "INSERT INTO " + style_table  + " VALUES(9, 'BCH-100' );";
        String styleInsert10 = "INSERT INTO " + style_table  + " VALUES(10, 'BCH-1013' );";
        String styleInsert11 = "INSERT INTO " + style_table  + " VALUES(11, 'BCT-100' );";
        String styleInsert12 = "INSERT INTO " + style_table  + " VALUES(12, 'BFZ-6009' );";
        String styleInsert13 = "INSERT INTO " + style_table  + " VALUES(13, 'BLH-1034' );";
        String styleInsert14 = "INSERT INTO " + style_table  + " VALUES(14, 'BLT-0521' );";
        String styleInsert15 = "INSERT INTO " + style_table  + " VALUES(15, 'BLT-0522' );";
        String styleInsert16 = "INSERT INTO " + style_table  + " VALUES(16, 'BLT-0523' );";
        String styleInsert17 = "INSERT INTO " + style_table  + " VALUES(17, 'BLT-1001' );";
        String styleInsert18 = "INSERT INTO " + style_table  + " VALUES(18, 'BLT-410' );";
        String styleInsert19 = "INSERT INTO " + style_table  + " VALUES(19, 'BLT-510' );";
        String styleInsert20 = "INSERT INTO " + style_table  + " VALUES(20, 'BLT-7110' );";
        String styleInsert21 = "INSERT INTO " + style_table  + " VALUES(21, 'BMF-1003' );";
        String styleInsert22 = "INSERT INTO " + style_table  + " VALUES(22, 'BY-026' );";
        String styleInsert23 = "INSERT INTO " + style_table  + " VALUES(23, 'BY-083B' );";
        String styleInsert24 = "INSERT INTO " + style_table  + " VALUES(24, 'BY-108' );";
        String styleInsert25 = "INSERT INTO " + style_table  + " VALUES(25, 'BY-242' );";
        String styleInsert26 = "INSERT INTO " + style_table  + " VALUES(26, 'BY-247' );";
        String styleInsert27 = "INSERT INTO " + style_table  + " VALUES(27, 'BYS-108' );";
        String styleInsert28 = "INSERT INTO " + style_table  + " VALUES(28, 'BYS-214' );";
        String styleInsert29 = "INSERT INTO " + style_table  + " VALUES(29, 'CF-2000' );";
        String styleInsert30 = "INSERT INTO " + style_table  + " VALUES(30, 'CF-3000' );";
        String styleInsert31 = "INSERT INTO " + style_table  + " VALUES(31, 'FMH-0515' );";
        String styleInsert32 = "INSERT INTO " + style_table  + " VALUES(32, 'FTH-5010' );";
        String styleInsert33 = "INSERT INTO " + style_table  + " VALUES(33, 'FTS-1000' );";
        String styleInsert34 = "INSERT INTO " + style_table  + " VALUES(34, 'FTS-2400' );";
        String styleInsert35 = "INSERT INTO " + style_table  + " VALUES(35, 'FTS-6150' );";
        String styleInsert36 = "INSERT INTO " + style_table  + " VALUES(36, 'GFR-1088' );";
        String styleInsert37 = "INSERT INTO " + style_table  + " VALUES(37, 'GFR-6132' );";
        String styleInsert38 = "INSERT INTO " + style_table  + " VALUES(38, 'GJY-5144' );";
        String styleInsert39 = "INSERT INTO " + style_table  + " VALUES(39, 'GJY-5145' );";
        String styleInsert40 = "INSERT INTO " + style_table  + " VALUES(40, 'KFL-1146' );";
        String styleInsert41 = "INSERT INTO " + style_table  + " VALUES(41, 'KFL-2141' );";
        String styleInsert42 = "INSERT INTO " + style_table  + " VALUES(42, 'KFL-2209' );";
        String styleInsert43 = "INSERT INTO " + style_table  + " VALUES(43, 'LBS-111' );";
        String styleInsert44 = "INSERT INTO " + style_table  + " VALUES(44, 'LBS-112' );";
        String styleInsert45 = "INSERT INTO " + style_table  + " VALUES(45, 'LBS-115' );";
        String styleInsert46 = "INSERT INTO " + style_table  + " VALUES(46, 'LBS-119' );";
        String styleInsert47 = "INSERT INTO " + style_table  + " VALUES(47, 'LBS-121' );";
        String styleInsert48 = "INSERT INTO " + style_table  + " VALUES(48, 'LBS-158' );";
        String styleInsert49 = "INSERT INTO " + style_table  + " VALUES(49, 'LBS-159' );";
        String styleInsert50 = "INSERT INTO " + style_table  + " VALUES(50, 'LBS-172' );";
        String styleInsert51 = "INSERT INTO " + style_table  + " VALUES(51, 'LBS-179' );";
        String styleInsert52 = "INSERT INTO " + style_table  + " VALUES(52, 'MAS-2000' );";
        String styleInsert53 = "INSERT INTO " + style_table  + " VALUES(53, 'MAS-2202' );";
        String styleInsert54 = "INSERT INTO " + style_table  + " VALUES(54, 'MCT-100' );";
        String styleInsert55 = "INSERT INTO " + style_table  + " VALUES(55, 'MFL-1044' );";
        String styleInsert56 = "INSERT INTO " + style_table  + " VALUES(56, 'MJP-0526' );";
        String styleInsert57 = "INSERT INTO " + style_table  + " VALUES(57, 'MJP-1036' );";
        String styleInsert58 = "INSERT INTO " + style_table  + " VALUES(58, 'MJY-4047' );";
        String styleInsert59 = "INSERT INTO " + style_table  + " VALUES(59, 'MJY-4112' );";
        String styleInsert60 = "INSERT INTO " + style_table  + " VALUES(60, 'MMS-100' );";
        String styleInsert61 = "INSERT INTO " + style_table  + " VALUES(61, 'MMS-200' );";
        String styleInsert62 = "INSERT INTO " + style_table  + " VALUES(62, 'MMS-500' );";
        String styleInsert63 = "INSERT INTO " + style_table  + " VALUES(63, 'MMS-5154' );";
        String styleInsert64 = "INSERT INTO " + style_table  + " VALUES(64, 'MN-009' );";
        String styleInsert65 = "INSERT INTO " + style_table  + " VALUES(65, 'MN-026' );";
        String styleInsert66 = "INSERT INTO " + style_table  + " VALUES(66, 'MN-108' );";
        String styleInsert67 = "INSERT INTO " + style_table  + " VALUES(67, 'MN-155' );";
        String styleInsert68 = "INSERT INTO " + style_table  + " VALUES(68, 'MN-166' );";
        String styleInsert69 = "INSERT INTO " + style_table  + " VALUES(69, 'MN-167' );";
        String styleInsert70 = "INSERT INTO " + style_table  + " VALUES(70, 'MN-168' );";
        String styleInsert71 = "INSERT INTO " + style_table  + " VALUES(71, 'MN-170' );";
        String styleInsert72 = "INSERT INTO " + style_table  + " VALUES(72, 'MN-2000' );";
        String styleInsert73 = "INSERT INTO " + style_table  + " VALUES(73, 'MN-217' );";
        String styleInsert74 = "INSERT INTO " + style_table  + " VALUES(74, 'MN-219' );";
        String styleInsert75 = "INSERT INTO " + style_table  + " VALUES(75, 'MN-244' );";
        String styleInsert76 = "INSERT INTO " + style_table  + " VALUES(76, 'MN-253' );";
        String styleInsert77 = "INSERT INTO " + style_table  + " VALUES(77, 'MN-256' );";
        String styleInsert78 = "INSERT INTO " + style_table  + " VALUES(78, 'MN-257' );";
        String styleInsert79 = "INSERT INTO " + style_table  + " VALUES(79, 'MN-258' );";
        String styleInsert80 = "INSERT INTO " + style_table  + " VALUES(80, 'MNP-009' );";
        String styleInsert81 = "INSERT INTO " + style_table  + " VALUES(81, 'MNP-108' );";
        String styleInsert82 = "INSERT INTO " + style_table  + " VALUES(82, 'MNP-166' );";
        String styleInsert83 = "INSERT INTO " + style_table  + " VALUES(83, 'MNS-176' );";
        String styleInsert84 = "INSERT INTO " + style_table  + " VALUES(84, 'MPS-191' );";
        String styleInsert85 = "INSERT INTO " + style_table  + " VALUES(85, 'MPS-210' );";
        String styleInsert86 = "INSERT INTO " + style_table  + " VALUES(86, 'MPS-234' );";
        String styleInsert87 = "INSERT INTO " + style_table  + " VALUES(87, 'MPS-235' );";
        String styleInsert88 = "INSERT INTO " + style_table  + " VALUES(88, 'MPS-248' );";
        String styleInsert89 = "INSERT INTO " + style_table  + " VALUES(89, 'MPS-249' );";
        String styleInsert90 = "INSERT INTO " + style_table  + " VALUES(90, 'MPS-252' );";
        String styleInsert91 = "INSERT INTO " + style_table  + " VALUES(91, 'MPS-260' );";
        String styleInsert92 = "INSERT INTO " + style_table  + " VALUES(92, 'MPS-261' );";
        String styleInsert93 = "INSERT INTO " + style_table  + " VALUES(93, 'MPS-262' );";
        String styleInsert94 = "INSERT INTO " + style_table  + " VALUES(94, 'MPS-264' );";
        String styleInsert95 = "INSERT INTO " + style_table  + " VALUES(95, 'MPS-265' );";
        String styleInsert96 = "INSERT INTO " + style_table  + " VALUES(96, 'MPS-266' );";
        String styleInsert97 = "INSERT INTO " + style_table  + " VALUES(97, 'MPS-267' );";
        String styleInsert98 = "INSERT INTO " + style_table  + " VALUES(98, 'MSE-216' );";
        String styleInsert99 = "INSERT INTO " + style_table  + " VALUES(99, 'MSE-228' );";
        String styleInsert100 = "INSERT INTO " + style_table  + " VALUES(100, 'MSE-229' );";
        String styleInsert101 = "INSERT INTO " + style_table  + " VALUES(101, 'MSE-232' );";
        String styleInsert102 = "INSERT INTO " + style_table  + " VALUES(102, 'MSE-254' );";
        String styleInsert103 = "INSERT INTO " + style_table  + " VALUES(103, 'MSE-255' );";
        String styleInsert104 = "INSERT INTO " + style_table  + " VALUES(104, 'MSE-263' );";
        String styleInsert105 = "INSERT INTO " + style_table  + " VALUES(105, 'MSP-259' );";
        String styleInsert106 = "INSERT INTO " + style_table  + " VALUES(106, 'MTT-100' );";
        String styleInsert107 = "INSERT INTO " + style_table  + " VALUES(107, 'MTT-1035' );";
        String styleInsert108 = "INSERT INTO " + style_table  + " VALUES(108, 'MTT-1038' );";
        String styleInsert109 = "INSERT INTO " + style_table  + " VALUES(109, 'MTT-1047' );";
        String styleInsert110 = "INSERT INTO " + style_table  + " VALUES(110, 'MTT-310' );";
        String styleInsert111 = "INSERT INTO " + style_table  + " VALUES(111, 'MTT-400' );";
        String styleInsert112 = "INSERT INTO " + style_table  + " VALUES(112, 'MTT-500' );";
        String styleInsert113 = "INSERT INTO " + style_table  + " VALUES(113, 'MTT-5092' );";
        String styleInsert114 = "INSERT INTO " + style_table  + " VALUES(114, 'MTT-5155' );";
        String styleInsert115 = "INSERT INTO " + style_table  + " VALUES(115, 'MTT-5169' );";
        String styleInsert116 = "INSERT INTO " + style_table  + " VALUES(116, 'MTT-5170' );";
        String styleInsert117 = "INSERT INTO " + style_table  + " VALUES(117, 'MTT-5227' );";
        String styleInsert118 = "INSERT INTO " + style_table  + " VALUES(118, 'MTT-5228' );";
        String styleInsert119 = "INSERT INTO " + style_table  + " VALUES(119, 'MTT-600' );";
        String styleInsert120 = "INSERT INTO " + style_table  + " VALUES(120, 'MTTP-100' );";
        String styleInsert121 = "INSERT INTO " + style_table  + " VALUES(121, 'MX-ZAFLC' );";
        String styleInsert122 = "INSERT INTO " + style_table  + " VALUES(122, 'N-17' );";
        String styleInsert123 = "INSERT INTO " + style_table  + " VALUES(123, 'PDY-213' );";
        String styleInsert124 = "INSERT INTO " + style_table  + " VALUES(124, 'PH001' );";
        String styleInsert125 = "INSERT INTO " + style_table  + " VALUES(125, 'RGB-1020' );";
        String styleInsert126 = "INSERT INTO " + style_table  + " VALUES(126, 'RGB-1021' );";
        String styleInsert127 = "INSERT INTO " + style_table  + " VALUES(127, 'RGG-122' );";
        String styleInsert128 = "INSERT INTO " + style_table  + " VALUES(128, 'RGL-1018' );";
        String styleInsert129 = "INSERT INTO " + style_table  + " VALUES(129, 'RGL-1019' );";
        String styleInsert130 = "INSERT INTO " + style_table  + " VALUES(130, 'RGL-1099' );";
        String styleInsert131 = "INSERT INTO " + style_table  + " VALUES(131, 'RGM-1014' );";
        String styleInsert132 = "INSERT INTO " + style_table  + " VALUES(132, 'RGM-1015' );";
        String styleInsert133 = "INSERT INTO " + style_table  + " VALUES(133, 'RGM-1017' );";
        String styleInsert134 = "INSERT INTO " + style_table  + " VALUES(134, 'RGM-1016' );";
        String styleInsert135 = "INSERT INTO " + style_table  + " VALUES(135, 'RSC-2500' );";
        String styleInsert136 = "INSERT INTO " + style_table  + " VALUES(136, 'SJY-7066' );";
        String styleInsert137 = "INSERT INTO " + style_table  + " VALUES(137, 'SLH-1008' );";
        String styleInsert138 = "INSERT INTO " + style_table  + " VALUES(138, 'SLT-0525' );";
        String styleInsert139 = "INSERT INTO " + style_table  + " VALUES(139, 'SLT-5061' );";
        String styleInsert140 = "INSERT INTO " + style_table  + " VALUES(140, 'SRT-100' );";
        String styleInsert141 = "INSERT INTO " + style_table  + " VALUES(141, 'SSH-100' );";
        String styleInsert142 = "INSERT INTO " + style_table  + " VALUES(142, 'SSL-4062' );";
        String styleInsert143 = "INSERT INTO " + style_table  + " VALUES(143, 'UFL-1041' );";
        String styleInsert144 = "INSERT INTO " + style_table  + " VALUES(144, 'UFL-1046' );";
        String styleInsert145 = "INSERT INTO " + style_table  + " VALUES(145, 'UFL-1128' );";
        String styleInsert146 = "INSERT INTO " + style_table  + " VALUES(146, 'UFL-1135' );";
        String styleInsert147 = "INSERT INTO " + style_table  + " VALUES(147, 'UFL-1162' );";
        String styleInsert148 = "INSERT INTO " + style_table  + " VALUES(148, 'UFL-1181' );";
        String styleInsert149 = "INSERT INTO " + style_table  + " VALUES(149, 'UFL-1189' );";
        String styleInsert150 = "INSERT INTO " + style_table  + " VALUES(150, 'UFL-1191' );";
        String styleInsert151 = "INSERT INTO " + style_table  + " VALUES(151, 'UFL-1203' );";
        String styleInsert152 = "INSERT INTO " + style_table  + " VALUES(152, 'UFL-1206' );";
        String styleInsert153 = "INSERT INTO " + style_table  + " VALUES(153, 'UFL-1210' );";
        String styleInsert154 = "INSERT INTO " + style_table  + " VALUES(154, 'UFL-1284' );";
        String styleInsert155 = "INSERT INTO " + style_table  + " VALUES(155, 'UFL-1202' );";
        String styleInsert156 = "INSERT INTO " + style_table  + " VALUES(156, 'UFL-1236' );";
        String styleInsert157 = "INSERT INTO " + style_table  + " VALUES(157, 'UFL-1260' );";
        String styleInsert158 = "INSERT INTO " + style_table  + " VALUES(158, 'UFL-1282' );";
        String styleInsert159 = "INSERT INTO " + style_table  + " VALUES(159, 'UFL-2190' );";
        String styleInsert160 = "INSERT INTO " + style_table  + " VALUES(160, 'UFL-2192' );";
        String styleInsert161 = "INSERT INTO " + style_table  + " VALUES(161, 'UFL-2204' );";
        String styleInsert162 = "INSERT INTO " + style_table  + " VALUES(162, 'UFL-2205' );";
        String styleInsert163 = "INSERT INTO " + style_table  + " VALUES(163, 'UFL-2207' );";
        String styleInsert164 = "INSERT INTO " + style_table  + " VALUES(164, 'UFL-2208' );";
        String styleInsert165 = "INSERT INTO " + style_table  + " VALUES(165, 'UFL-2281' );";
        String styleInsert166 = "INSERT INTO " + style_table  + " VALUES(166, 'UFL-3202' );";
        String styleInsert167 = "INSERT INTO " + style_table  + " VALUES(167, 'UFL-3210' );";
        String styleInsert168 = "INSERT INTO " + style_table  + " VALUES(168, 'UFL-6158' );";
        String styleInsert169 = "INSERT INTO " + style_table  + " VALUES(169, 'UFL-6166' );";
        String styleInsert170 = "INSERT INTO " + style_table  + " VALUES(170, 'UFL-7159' );";
        String styleInsert171 = "INSERT INTO " + style_table  + " VALUES(171, 'UFL-7167' );";
        String styleInsert172 = "INSERT INTO " + style_table  + " VALUES(172, 'UFL-7186' );";
        String styleInsert173 = "INSERT INTO " + style_table  + " VALUES(173, 'UFL-7211' );";
        String styleInsert174 = "INSERT INTO " + style_table  + " VALUES(174, 'UFL-9208' );";
        String styleInsert175 = "INSERT INTO " + style_table  + " VALUES(175, 'UFR-1080' );";
        String styleInsert176 = "INSERT INTO " + style_table  + " VALUES(176, 'UFR-1108' );";
        String styleInsert177 = "INSERT INTO " + style_table  + " VALUES(177, 'UJY-1079' );";
        String styleInsert178 = "INSERT INTO " + style_table  + " VALUES(178, 'UJY-1180' );";
        String styleInsert179 = "INSERT INTO " + style_table  + " VALUES(179, 'UJY-1070000' );";
        String styleInsert180 = "INSERT INTO " + style_table  + " VALUES(180, 'UJY-4131' );";
        String styleInsert181 = "INSERT INTO " + style_table  + " VALUES(181, 'UJY-4149' );";
        String styleInsert182 = "INSERT INTO " + style_table  + " VALUES(182, 'UJY-4156' );";
        String styleInsert183 = "INSERT INTO " + style_table  + " VALUES(183, 'UJY-4157' );";
        String styleInsert184 = "INSERT INTO " + style_table  + " VALUES(184, 'UJY-4161' );";
        String styleInsert185 = "INSERT INTO " + style_table  + " VALUES(185, 'UJY-4193' );";
        String styleInsert186 = "INSERT INTO " + style_table  + " VALUES(186, 'UJY-1205' );";
        String styleInsert187 = "INSERT INTO " + style_table  + " VALUES(187, 'VCP-100' );";
        String styleInsert188 = "INSERT INTO " + style_table  + " VALUES(188, 'WHD-5003' );";
        String styleInsert189 = "INSERT INTO " + style_table  + " VALUES(189, 'WHD-5005' );";
        String styleInsert190 = "INSERT INTO " + style_table  + " VALUES(190, 'WHD-5009' );";
        String styleInsert191 = "INSERT INTO " + style_table  + " VALUES(191, 'WJS-0511' );";
        String styleInsert192 = "INSERT INTO " + style_table  + " VALUES(192, 'YFL-1053' );";
        String styleInsert193 = "INSERT INTO " + style_table  + " VALUES(193, 'YFL-1113' );";
        String styleInsert194 = "INSERT INTO " + style_table  + " VALUES(194, 'YFL-1118' );";
        String styleInsert195 = "INSERT INTO " + style_table  + " VALUES(195, 'YFL-1120' );";
        String styleInsert196 = "INSERT INTO " + style_table  + " VALUES(196, 'YFL-1140' );";
        String styleInsert197 = "INSERT INTO " + style_table  + " VALUES(197, 'YFL-1147' );";
        String styleInsert198 = "INSERT INTO " + style_table  + " VALUES(198, 'YFL-1170' );";
        String styleInsert199 = "INSERT INTO " + style_table  + " VALUES(199, 'YFL-1183' );";
        String styleInsert200 = "INSERT INTO " + style_table  + " VALUES(200, 'YFL-2134' );";
        String styleInsert201 = "INSERT INTO " + style_table  + " VALUES(201, 'YFL-2148' );";
        String styleInsert202 = "INSERT INTO " + style_table  + " VALUES(202, 'YFL-2171' );";
        String styleInsert203 = "INSERT INTO " + style_table  + " VALUES(203, 'YFL-2188' );";
        String styleInsert204 = "INSERT INTO " + style_table  + " VALUES(204, 'YFL-3114' );";
        String styleInsert205 = "INSERT INTO " + style_table  + " VALUES(205, 'YFL-6124' );";
        String styleInsert206 = "INSERT INTO " + style_table  + " VALUES(206, 'YFL-6163' );";
        String styleInsert207 = "INSERT INTO " + style_table  + " VALUES(207, 'YFL-6185' );";
        String styleInsert208 = "INSERT INTO " + style_table  + " VALUES(208, 'YFL-7168' );";
        String styleInsert209 = "INSERT INTO " + style_table  + " VALUES(209, 'YFL-7190' );";
        String styleInsert210 = "INSERT INTO " + style_table  + " VALUES(210, 'YFR-1049' );";
        String styleInsert211 = "INSERT INTO " + style_table  + " VALUES(211, 'YFR-104' );";
        String styleInsert212 = "INSERT INTO " + style_table  + " VALUES(212, 'YFR-1051' );";
        String styleInsert213 = "INSERT INTO " + style_table  + " VALUES(213, 'YFR-1058' );";
        String styleInsert214 = "INSERT INTO " + style_table  + " VALUES(214, 'YFR-1059' );";
        String styleInsert215 = "INSERT INTO " + style_table  + " VALUES(215, 'YFR-1106' );";
        String styleInsert216 = "INSERT INTO " + style_table  + " VALUES(216, 'YFR-1110' );";
        String styleInsert217 = "INSERT INTO " + style_table  + " VALUES(217, 'YFR-1139' );";
        String styleInsert218 = "INSERT INTO " + style_table  + " VALUES(218, 'YFR-1175' );";
        String styleInsert219 = "INSERT INTO " + style_table  + " VALUES(219, 'YFR-4143' );";
        String styleInsert220 = "INSERT INTO " + style_table  + " VALUES(220, 'YFR-6071' );";
        String styleInsert221 = "INSERT INTO " + style_table  + " VALUES(221, 'YFR-6088' );";
        String styleInsert222 = "INSERT INTO " + style_table  + " VALUES(222, 'YFR-6089' );";
        String styleInsert223 = "INSERT INTO " + style_table  + " VALUES(223, 'YJY-1165' );";
        String styleInsert224 = "INSERT INTO " + style_table  + " VALUES(224, 'YJY-4085' );";
        String styleInsert225 = "INSERT INTO " + style_table  + " VALUES(225, 'YJY-4129' );";
        String styleInsert226 = "INSERT INTO " + style_table  + " VALUES(226, 'YJY-4137' );";
        String styleInsert227 = "INSERT INTO " + style_table  + " VALUES(227, 'YJY-4138' );";
        String styleInsert228 = "INSERT INTO " + style_table  + " VALUES(228, 'YJY-4139' );";
        String styleInsert229 = "INSERT INTO " + style_table  + " VALUES(229, 'YJY-4142' );";
        String styleInsert230 = "INSERT INTO " + style_table  + " VALUES(230, 'YJY-4144' );";
        String styleInsert231 = "INSERT INTO " + style_table  + " VALUES(231, 'YJY-4145' );";
        String styleInsert232 = "INSERT INTO " + style_table  + " VALUES(232, 'YJY-4151' );";
        String styleInsert233 = "INSERT INTO " + style_table  + " VALUES(233, 'YJY-4152' );";
        String styleInsert234 = "INSERT INTO " + style_table  + " VALUES(234, 'YJY-4153' );";
        String styleInsert235 = "INSERT INTO " + style_table  + " VALUES(235, 'YJY-5008' );";
        String styleInsert236 = "INSERT INTO " + style_table  + " VALUES(236, 'YJY-5081' );";
        String styleInsert237 = "INSERT INTO " + style_table  + " VALUES(237, 'YJY-5082' );";
        String styleInsert238 = "INSERT INTO " + style_table  + " VALUES(238, 'YJY-5101' );";
        String styleInsert239 = "INSERT INTO " + style_table  + " VALUES(239, 'YJY-5103' );";
        String styleInsert240 = "INSERT INTO " + style_table  + " VALUES(240, 'YJY-5109' );";
        String styleInsert241 = "INSERT INTO " + style_table  + " VALUES(241, 'YJY-5158' );";
        String styleInsert242 = "INSERT INTO " + style_table  + " VALUES(242, 'YJY-5164' );";
        String styleInsert243 = "INSERT INTO " + style_table  + " VALUES(243, 'YJY-5177' );";
        String styleInsert244 = "INSERT INTO " + style_table  + " VALUES(244, 'YJY-5184' );";
        String styleInsert245 = "INSERT INTO " + style_table  + " VALUES(245, 'YMS-100' );";
        String styleInsert246 = "INSERT INTO " + style_table  + " VALUES(246, 'YRY-1130' );";
        String styleInsert247 = "INSERT INTO " + style_table  + " VALUES(247, 'YRY-113' );";
        String styleInsert248 = "INSERT INTO " + style_table  + " VALUES(248, 'YRY-1178' );";
        String styleInsert249 = "INSERT INTO " + style_table  + " VALUES(249, 'YSL-4125' );";
        String styleInsert250 = "INSERT INTO " + style_table  + " VALUES(250, 'YSL-5095' );";


        String insert1 = "INSERT INTO " + location_table  + " VALUES(1, '01-003-C1' );";
        String insert2 = "INSERT INTO " + location_table  + " VALUES(2, '01-003-D1' );";
        String insert3 = "INSERT INTO " + location_table  + " VALUES(3, '01-003-E1' );";
        String insert4 = "INSERT INTO " + location_table  + " VALUES(4, '01-003-C2' );";
        String insert5 = "INSERT INTO " + location_table  + " VALUES(5, '01-003-D2' );";
        String insert6 = "INSERT INTO " + location_table  + " VALUES(6, '01-003-E2' );";
        String insert7 = "INSERT INTO " + location_table  + " VALUES(7, '01-005-C1' );";
        String insert8 = "INSERT INTO " + location_table  + " VALUES(8, '01-005-D1' );";
        String insert9 = "INSERT INTO " + location_table  + " VALUES(9, '01-005-E1' );";
        String insert10 = "INSERT INTO " + location_table  + " VALUES(10, '01-005-C2' );";
        String insert11 = "INSERT INTO " + location_table  + " VALUES(11, '01-005-D2' );";
        String insert12 = "INSERT INTO " + location_table  + " VALUES(12, '01-005-E2' );";
        String insert13 = "INSERT INTO " + location_table  + " VALUES(13, '01-007-C1' );";
        String insert14 = "INSERT INTO " + location_table  + " VALUES(14, '01-007-D1' );";
        String insert15 = "INSERT INTO " + location_table  + " VALUES(15, '01-007-E1' );";
        String insert16 = "INSERT INTO " + location_table  + " VALUES(16, '01-007-C2' );";
        String insert17 = "INSERT INTO " + location_table  + " VALUES(17, '01-007-D2' );";
        String insert18 = "INSERT INTO " + location_table  + " VALUES(18, '01-007-E2' );";
        String insert19 = "INSERT INTO " + location_table  + " VALUES(19, '01-009-C1' );";
        String insert20 = "INSERT INTO " + location_table  + " VALUES(20, '01-009-D1' );";
        String insert21 = "INSERT INTO " + location_table  + " VALUES(21, '01-009-E1' );";
        String insert22 = "INSERT INTO " + location_table  + " VALUES(22, '01-009-C2' );";
        String insert23 = "INSERT INTO " + location_table  + " VALUES(23, '01-009-D2' );";
        String insert24 = "INSERT INTO " + location_table  + " VALUES(24, '01-009-E2' );";
        String insert25 = "INSERT INTO " + location_table  + " VALUES(25, '01-011-C1' );";
        String insert26 = "INSERT INTO " + location_table  + " VALUES(26, '01-011-D1' );";
        String insert27 = "INSERT INTO " + location_table  + " VALUES(27, '01-011-E1' );";
        String insert28 = "INSERT INTO " + location_table  + " VALUES(28, '01-011-C2' );";
        String insert29 = "INSERT INTO " + location_table  + " VALUES(29, '01-011-D2' );";
        String insert30 = "INSERT INTO " + location_table  + " VALUES(30, '01-011-E2' );";
        String insert31 = "INSERT INTO " + location_table  + " VALUES(31, '01-013-C1' );";
        String insert32 = "INSERT INTO " + location_table  + " VALUES(32, '01-013-D1' );";
        String insert33 = "INSERT INTO " + location_table  + " VALUES(33, '01-013-E1' );";
        String insert34 = "INSERT INTO " + location_table  + " VALUES(34, '01-013-C2' );";
        String insert35 = "INSERT INTO " + location_table  + " VALUES(35, '01-013-D2' );";
        String insert36 = "INSERT INTO " + location_table  + " VALUES(36, '01-013-E2' );";
        String insert37 = "INSERT INTO " + location_table  + " VALUES(37, '01-015-C1' );";
        String insert38 = "INSERT INTO " + location_table  + " VALUES(38, '01-015-D1' );";
        String insert39 = "INSERT INTO " + location_table  + " VALUES(39, '01-015-E1' );";
        String insert40 = "INSERT INTO " + location_table  + " VALUES(40, '01-015-C2' );";
        String insert41 = "INSERT INTO " + location_table  + " VALUES(41, '01-015-D2' );";
        String insert42 = "INSERT INTO " + location_table  + " VALUES(42, '01-015-E2' );";
        String insert43 = "INSERT INTO " + location_table  + " VALUES(43, '01-017-C1' );";
        String insert44 = "INSERT INTO " + location_table  + " VALUES(44, '01-017-D1' );";
        String insert45 = "INSERT INTO " + location_table  + " VALUES(45, '01-017-E1' );";
        String insert46 = "INSERT INTO " + location_table  + " VALUES(46, '01-017-C2' );";
        String insert47 = "INSERT INTO " + location_table  + " VALUES(47, '01-017-D2' );";
        String insert48 = "INSERT INTO " + location_table  + " VALUES(48, '01-017-E2' );";
        String insert49 = "INSERT INTO " + location_table  + " VALUES(49, '01-019-C1' );";
        String insert50 = "INSERT INTO " + location_table  + " VALUES(50, '01-019-D1' );";
        String insert51 = "INSERT INTO " + location_table  + " VALUES(51, '01-019-E1' );";
        String insert52 = "INSERT INTO " + location_table  + " VALUES(52, '01-019-C2' );";
        String insert53 = "INSERT INTO " + location_table  + " VALUES(53, '01-019-D2' );";
        String insert54 = "INSERT INTO " + location_table  + " VALUES(54, '01-019-E2' );";
        String insert55 = "INSERT INTO " + location_table  + " VALUES(55, '01-021-C1' );";
        String insert56 = "INSERT INTO " + location_table  + " VALUES(56, '01-021-D1' );";
        String insert57 = "INSERT INTO " + location_table  + " VALUES(57, '01-021-E1' );";
        String insert58 = "INSERT INTO " + location_table  + " VALUES(58, '01-021-C2' );";
        String insert59 = "INSERT INTO " + location_table  + " VALUES(59, '01-021-D2' );";
        String insert60 = "INSERT INTO " + location_table  + " VALUES(60, '01-021-E2' );";
        String insert61 = "INSERT INTO " + location_table  + " VALUES(61, '01-023-C1' );";
        String insert62 = "INSERT INTO " + location_table  + " VALUES(62, '01-023-D1' );";
        String insert63 = "INSERT INTO " + location_table  + " VALUES(63, '01-023-E1' );";
        String insert64 = "INSERT INTO " + location_table  + " VALUES(64, '01-023-C2' );";
        String insert65 = "INSERT INTO " + location_table  + " VALUES(65, '01-023-D2' );";
        String insert66 = "INSERT INTO " + location_table  + " VALUES(66, '01-023-E2' );";
        String insert67 = "INSERT INTO " + location_table  + " VALUES(67, '01-025-C1' );";
        String insert68 = "INSERT INTO " + location_table  + " VALUES(68, '01-025-D1' );";
        String insert69 = "INSERT INTO " + location_table  + " VALUES(69, '01-025-E1' );";
        String insert70 = "INSERT INTO " + location_table  + " VALUES(70, '01-025-C2' );";
        String insert71 = "INSERT INTO " + location_table  + " VALUES(71, '01-025-D2' );";
        String insert72 = "INSERT INTO " + location_table  + " VALUES(72, '01-025-E2' );";
        String insert73 = "INSERT INTO " + location_table  + " VALUES(73, '01-027-C1' );";
        String insert74 = "INSERT INTO " + location_table  + " VALUES(74, '01-027-D1' );";
        String insert75 = "INSERT INTO " + location_table  + " VALUES(75, '01-027-E1' );";
        String insert76 = "INSERT INTO " + location_table  + " VALUES(76, '01-027-C2' );";
        String insert77 = "INSERT INTO " + location_table  + " VALUES(77, '01-027-D2' );";
        String insert78 = "INSERT INTO " + location_table  + " VALUES(78, '01-027-E2' );";
        String insert79 = "INSERT INTO " + location_table  + " VALUES(79, '01-029-C1' );";
        String insert80 = "INSERT INTO " + location_table  + " VALUES(80, '01-029-D1' );";
        String insert81 = "INSERT INTO " + location_table  + " VALUES(81, '01-029-E1' );";
        String insert82 = "INSERT INTO " + location_table  + " VALUES(82, '01-029-C2' );";
        String insert83 = "INSERT INTO " + location_table  + " VALUES(83, '01-029-D2' );";
        String insert84 = "INSERT INTO " + location_table  + " VALUES(84, '01-029-E2' );";
        String insert85 = "INSERT INTO " + location_table  + " VALUES(85, '01-006-C1' );";
        String insert86 = "INSERT INTO " + location_table  + " VALUES(86, '01-006-D1' );";
        String insert87 = "INSERT INTO " + location_table  + " VALUES(87, '01-006-E1' );";
        String insert88 = "INSERT INTO " + location_table  + " VALUES(88, '01-006-C2' );";
        String insert89 = "INSERT INTO " + location_table  + " VALUES(89, '01-006-D2' );";
        String insert90 = "INSERT INTO " + location_table  + " VALUES(90, '01-006-E2' );";
        String insert91 = "INSERT INTO " + location_table  + " VALUES(91, '01-008-C1' );";
        String insert92 = "INSERT INTO " + location_table  + " VALUES(92, '01-008-D1' );";
        String insert93 = "INSERT INTO " + location_table  + " VALUES(93, '01-008-E1' );";
        String insert94 = "INSERT INTO " + location_table  + " VALUES(94, '01-008-C2' );";
        String insert95 = "INSERT INTO " + location_table  + " VALUES(95, '01-008-D2' );";
        String insert96 = "INSERT INTO " + location_table  + " VALUES(96, '01-008-E2' );";
        String insert97 = "INSERT INTO " + location_table  + " VALUES(97, '01-010-C1' );";
        String insert98 = "INSERT INTO " + location_table  + " VALUES(98, '01-010-D1' );";
        String insert99 = "INSERT INTO " + location_table  + " VALUES(99, '01-010-E1' );";
        String insert100 = "INSERT INTO " + location_table  + " VALUES(100, '01-010-C2' );";
        String insert101 = "INSERT INTO " + location_table  + " VALUES(101, '01-010-D2' );";
        String insert102 = "INSERT INTO " + location_table  + " VALUES(102, '01-010-E2' );";
        String insert103 = "INSERT INTO " + location_table  + " VALUES(103, '01-012-C1' );";
        String insert104 = "INSERT INTO " + location_table  + " VALUES(104, '01-012-D1' );";
        String insert105 = "INSERT INTO " + location_table  + " VALUES(105, '01-012-E1' );";
        String insert106 = "INSERT INTO " + location_table  + " VALUES(106, '01-012-C2' );";
        String insert107 = "INSERT INTO " + location_table  + " VALUES(107, '01-012-D2' );";
        String insert108 = "INSERT INTO " + location_table  + " VALUES(108, '01-012-E2' );";
        String insert109 = "INSERT INTO " + location_table  + " VALUES(109, '01-014-C1' );";
        String insert110 = "INSERT INTO " + location_table  + " VALUES(110, '01-014-D1' );";
        String insert111 = "INSERT INTO " + location_table  + " VALUES(111, '01-014-E1' );";
        String insert112 = "INSERT INTO " + location_table  + " VALUES(112, '01-014-C2' );";
        String insert113 = "INSERT INTO " + location_table  + " VALUES(113, '01-014-D2' );";
        String insert114 = "INSERT INTO " + location_table  + " VALUES(114, '01-014-E2' );";
        String insert115 = "INSERT INTO " + location_table  + " VALUES(115, '01-016-C1' );";
        String insert116 = "INSERT INTO " + location_table  + " VALUES(116, '01-016-D1' );";
        String insert117 = "INSERT INTO " + location_table  + " VALUES(117, '01-016-E1' );";
        String insert118 = "INSERT INTO " + location_table  + " VALUES(118, '01-016-C2' );";
        String insert119 = "INSERT INTO " + location_table  + " VALUES(119, '01-016-D2' );";
        String insert120 = "INSERT INTO " + location_table  + " VALUES(120, '01-016-E2' );";
        String insert121 = "INSERT INTO " + location_table  + " VALUES(121, '01-018-C1' );";
        String insert122 = "INSERT INTO " + location_table  + " VALUES(122, '01-018-D1' );";
        String insert123 = "INSERT INTO " + location_table  + " VALUES(123, '01-018-E1' );";
        String insert124 = "INSERT INTO " + location_table  + " VALUES(124, '01-018-C2' );";
        String insert125 = "INSERT INTO " + location_table  + " VALUES(125, '01-018-D2' );";
        String insert126 = "INSERT INTO " + location_table  + " VALUES(126, '01-018-E2' );";
        String insert127 = "INSERT INTO " + location_table  + " VALUES(127, '01-020-C1' );";
        String insert128 = "INSERT INTO " + location_table  + " VALUES(128, '01-020-D1' );";
        String insert129 = "INSERT INTO " + location_table  + " VALUES(129, '01-020-E1' );";
        String insert130 = "INSERT INTO " + location_table  + " VALUES(130, '01-020-C2' );";
        String insert131 = "INSERT INTO " + location_table  + " VALUES(131, '01-020-D2' );";
        String insert132 = "INSERT INTO " + location_table  + " VALUES(132, '01-020-E2' );";
        String insert133 = "INSERT INTO " + location_table  + " VALUES(133, '01-022-C1' );";
        String insert134 = "INSERT INTO " + location_table  + " VALUES(134, '01-022-D1' );";
        String insert135 = "INSERT INTO " + location_table  + " VALUES(135, '01-022-E1' );";
        String insert136 = "INSERT INTO " + location_table  + " VALUES(136, '01-022-C2' );";
        String insert137 = "INSERT INTO " + location_table  + " VALUES(137, '01-022-D2' );";
        String insert138 = "INSERT INTO " + location_table  + " VALUES(138, '01-022-E2' );";
        String insert139 = "INSERT INTO " + location_table  + " VALUES(139, '01-024-B1' );";
        String insert140 = "INSERT INTO " + location_table  + " VALUES(140, '01-024-C1' );";
        String insert141 = "INSERT INTO " + location_table  + " VALUES(141, '01-024-D1' );";
        String insert142 = "INSERT INTO " + location_table  + " VALUES(142, '01-024-E1' );";
        String insert143 = "INSERT INTO " + location_table  + " VALUES(143, '01-024-B2' );";
        String insert144 = "INSERT INTO " + location_table  + " VALUES(144, '01-024-C2' );";
        String insert145 = "INSERT INTO " + location_table  + " VALUES(145, '01-024-D2' );";
        String insert146 = "INSERT INTO " + location_table  + " VALUES(146, '01-024-E2' );";
        String insert147 = "INSERT INTO " + location_table  + " VALUES(147, '01-026-C1' );";
        String insert148 = "INSERT INTO " + location_table  + " VALUES(148, '01-026-D1' );";
        String insert149 = "INSERT INTO " + location_table  + " VALUES(149, '01-026-E1' );";
        String insert150 = "INSERT INTO " + location_table  + " VALUES(150, '01-026-C2' );";
        String insert151 = "INSERT INTO " + location_table  + " VALUES(151, '01-026-D2' );";
        String insert152 = "INSERT INTO " + location_table  + " VALUES(152, '01-026-E2' );";
        String insert153 = "INSERT INTO " + location_table  + " VALUES(153, '01-028-C1' );";
        String insert154 = "INSERT INTO " + location_table  + " VALUES(154, '01-028-D1' );";
        String insert155 = "INSERT INTO " + location_table  + " VALUES(155, '01-028-E1' );";
        String insert156 = "INSERT INTO " + location_table  + " VALUES(156, '01-028-C2' );";
        String insert157 = "INSERT INTO " + location_table  + " VALUES(157, '01-028-D2' );";
        String insert158 = "INSERT INTO " + location_table  + " VALUES(158, '01-028-E2' );";
        String insert159 = "INSERT INTO " + location_table  + " VALUES(159, '02-001-C1' );";
        String insert160 = "INSERT INTO " + location_table  + " VALUES(160, '02-001-D1' );";
        String insert161 = "INSERT INTO " + location_table  + " VALUES(161, '02-001-E1' );";
        String insert162 = "INSERT INTO " + location_table  + " VALUES(162, '02-001-C2' );";
        String insert163 = "INSERT INTO " + location_table  + " VALUES(163, '02-001-D2' );";
        String insert164 = "INSERT INTO " + location_table  + " VALUES(164, '02-001-E2' );";
        String insert165 = "INSERT INTO " + location_table  + " VALUES(165, '02-003-D1' );";
        String insert166 = "INSERT INTO " + location_table  + " VALUES(166, '02-003-E1' );";
        String insert167 = "INSERT INTO " + location_table  + " VALUES(167, '02-003-F1' );";
        String insert168 = "INSERT INTO " + location_table  + " VALUES(168, '02-003-D2' );";
        String insert169 = "INSERT INTO " + location_table  + " VALUES(169, '02-003-E2' );";
        String insert170 = "INSERT INTO " + location_table  + " VALUES(170, '02-003-F2' );";
        String insert171 = "INSERT INTO " + location_table  + " VALUES(171, '02-005-D1' );";
        String insert172 = "INSERT INTO " + location_table  + " VALUES(172, '02-005-E1' );";
        String insert173 = "INSERT INTO " + location_table  + " VALUES(173, '02-005-F1' );";
        String insert174 = "INSERT INTO " + location_table  + " VALUES(174, '02-005-D2' );";
        String insert175 = "INSERT INTO " + location_table  + " VALUES(175, '02-005-E2' );";
        String insert176 = "INSERT INTO " + location_table  + " VALUES(176, '02-005-F2' );";
        String insert177 = "INSERT INTO " + location_table  + " VALUES(177, '02-007-D1' );";
        String insert178 = "INSERT INTO " + location_table  + " VALUES(178, '02-007-E1' );";
        String insert179 = "INSERT INTO " + location_table  + " VALUES(179, '02-007-F1' );";
        String insert180 = "INSERT INTO " + location_table  + " VALUES(180, '02-007-G1' );";
        String insert181 = "INSERT INTO " + location_table  + " VALUES(181, '02-007-D2' );";
        String insert182 = "INSERT INTO " + location_table  + " VALUES(182, '02-007-E2' );";
        String insert183 = "INSERT INTO " + location_table  + " VALUES(183, '02-007-F2' );";
        String insert184 = "INSERT INTO " + location_table  + " VALUES(184, '02-007-G2' );";
        String insert185 = "INSERT INTO " + location_table  + " VALUES(185, '02-009-D1' );";
        String insert186 = "INSERT INTO " + location_table  + " VALUES(186, '02-009-E1' );";
        String insert187 = "INSERT INTO " + location_table  + " VALUES(187, '02-009-F1' );";
        String insert188 = "INSERT INTO " + location_table  + " VALUES(188, '02-009-G1' );";
        String insert189 = "INSERT INTO " + location_table  + " VALUES(189, '02-009-D2' );";
        String insert190 = "INSERT INTO " + location_table  + " VALUES(190, '02-009-E2' );";
        String insert191 = "INSERT INTO " + location_table  + " VALUES(191, '02-009-F2' );";
        String insert192 = "INSERT INTO " + location_table  + " VALUES(192, '02-009-G2' );";
        String insert193 = "INSERT INTO " + location_table  + " VALUES(193, '02-011-D1' );";
        String insert194 = "INSERT INTO " + location_table  + " VALUES(194, '02-011-E1' );";
        String insert195 = "INSERT INTO " + location_table  + " VALUES(195, '02-011-F1' );";
        String insert196 = "INSERT INTO " + location_table  + " VALUES(196, '02-011-G1' );";
        String insert197 = "INSERT INTO " + location_table  + " VALUES(197, '02-011-D2' );";
        String insert198 = "INSERT INTO " + location_table  + " VALUES(198, '02-011-E2' );";
        String insert199 = "INSERT INTO " + location_table  + " VALUES(199, '02-011-F2' );";
        String insert200 = "INSERT INTO " + location_table  + " VALUES(200, '02-011-G2' );";
        String insert201 = "INSERT INTO " + location_table  + " VALUES(201, '02-013-D1' );";
        String insert202 = "INSERT INTO " + location_table  + " VALUES(202, '02-013-E1' );";
        String insert203 = "INSERT INTO " + location_table  + " VALUES(203, '02-013-F1' );";
        String insert204 = "INSERT INTO " + location_table  + " VALUES(204, '02-013-G1' );";
        String insert205 = "INSERT INTO " + location_table  + " VALUES(205, '02-013-D2' );";
        String insert206 = "INSERT INTO " + location_table  + " VALUES(206, '02-013-E2' );";
        String insert207 = "INSERT INTO " + location_table  + " VALUES(207, '02-013-F2' );";
        String insert208 = "INSERT INTO " + location_table  + " VALUES(208, '02-013-G2' );";
        String insert209 = "INSERT INTO " + location_table  + " VALUES(209, '02-015-D1' );";
        String insert210 = "INSERT INTO " + location_table  + " VALUES(210, '02-015-E1' );";
        String insert211 = "INSERT INTO " + location_table  + " VALUES(211, '02-015-F1' );";
        String insert212 = "INSERT INTO " + location_table  + " VALUES(212, '02-015-G1' );";
        String insert213 = "INSERT INTO " + location_table  + " VALUES(213, '02-015-D2' );";
        String insert214 = "INSERT INTO " + location_table  + " VALUES(214, '02-015-E2' );";
        String insert215 = "INSERT INTO " + location_table  + " VALUES(215, '02-015-F2' );";
        String insert216 = "INSERT INTO " + location_table  + " VALUES(216, '02-015-G2' );";
        String insert217 = "INSERT INTO " + location_table  + " VALUES(217, '02-017-D1' );";
        String insert218 = "INSERT INTO " + location_table  + " VALUES(218, '02-017-E1' );";
        String insert219 = "INSERT INTO " + location_table  + " VALUES(219, '02-017-F1' );";
        String insert220 = "INSERT INTO " + location_table  + " VALUES(220, '02-017-G1' );";
        String insert221 = "INSERT INTO " + location_table  + " VALUES(221, '02-017-D2' );";
        String insert222 = "INSERT INTO " + location_table  + " VALUES(222, '02-017-E2' );";
        String insert223 = "INSERT INTO " + location_table  + " VALUES(223, '02-017-F2' );";
        String insert224 = "INSERT INTO " + location_table  + " VALUES(224, '02-017-G2' );";
        String insert225 = "INSERT INTO " + location_table  + " VALUES(225, '02-019-D1' );";
        String insert226 = "INSERT INTO " + location_table  + " VALUES(226, '02-019-E1' );";
        String insert227 = "INSERT INTO " + location_table  + " VALUES(227, '02-019-F1' );";
        String insert228 = "INSERT INTO " + location_table  + " VALUES(228, '02-019-G1' );";
        String insert229 = "INSERT INTO " + location_table  + " VALUES(229, '02-019-D2' );";
        String insert230 = "INSERT INTO " + location_table  + " VALUES(230, '02-019-E2' );";
        String insert231 = "INSERT INTO " + location_table  + " VALUES(231, '02-019-F2' );";
        String insert232 = "INSERT INTO " + location_table  + " VALUES(232, '02-019-G2' );";
        String insert233 = "INSERT INTO " + location_table  + " VALUES(233, '02-021-D1' );";
        String insert234 = "INSERT INTO " + location_table  + " VALUES(234, '02-021-E1' );";
        String insert235 = "INSERT INTO " + location_table  + " VALUES(235, '02-021-F1' );";
        String insert236 = "INSERT INTO " + location_table  + " VALUES(236, '02-021-D2' );";
        String insert237 = "INSERT INTO " + location_table  + " VALUES(237, '02-021-E2' );";
        String insert238 = "INSERT INTO " + location_table  + " VALUES(238, '02-021-F2' );";
        String insert239 = "INSERT INTO " + location_table  + " VALUES(239, '02-023-D1' );";
        String insert240 = "INSERT INTO " + location_table  + " VALUES(240, '02-023-E1' );";
        String insert241 = "INSERT INTO " + location_table  + " VALUES(241, '02-023-F1' );";
        String insert242 = "INSERT INTO " + location_table  + " VALUES(242, '02-023-D2' );";
        String insert243 = "INSERT INTO " + location_table  + " VALUES(243, '02-023-E2' );";
        String insert244 = "INSERT INTO " + location_table  + " VALUES(244, '02-023-F2' );";
        String insert245 = "INSERT INTO " + location_table  + " VALUES(245, '02-002-E1' );";
        String insert246 = "INSERT INTO " + location_table  + " VALUES(246, '02-002-F1' );";
        String insert247 = "INSERT INTO " + location_table  + " VALUES(247, '02-002-G1' );";
        String insert248 = "INSERT INTO " + location_table  + " VALUES(248, '02-002-E2' );";
        String insert249 = "INSERT INTO " + location_table  + " VALUES(249, '02-002-F2' );";
        String insert250 = "INSERT INTO " + location_table  + " VALUES(250, '02-002-G2' );";
        String insert251 = "INSERT INTO " + location_table  + " VALUES(251, '02-004-C1' );";
        String insert252 = "INSERT INTO " + location_table  + " VALUES(252, '02-004-D1' );";
        String insert253 = "INSERT INTO " + location_table  + " VALUES(253, '02-004-E1' );";
        String insert254 = "INSERT INTO " + location_table  + " VALUES(254, '02-004-C2' );";
        String insert255 = "INSERT INTO " + location_table  + " VALUES(255, '02-004-D2' );";
        String insert256 = "INSERT INTO " + location_table  + " VALUES(256, '02-004-E2' );";
        String insert257 = "INSERT INTO " + location_table  + " VALUES(257, '02-006-C1' );";
        String insert258 = "INSERT INTO " + location_table  + " VALUES(258, '02-006-D1' );";
        String insert259 = "INSERT INTO " + location_table  + " VALUES(259, '02-006-E1' );";
        String insert260 = "INSERT INTO " + location_table  + " VALUES(260, '02-006-C2' );";
        String insert261 = "INSERT INTO " + location_table  + " VALUES(261, '02-006-D2' );";
        String insert262 = "INSERT INTO " + location_table  + " VALUES(262, '02-006-E2' );";
        String insert263 = "INSERT INTO " + location_table  + " VALUES(263, '02-008-C1' );";
        String insert264 = "INSERT INTO " + location_table  + " VALUES(264, '02-008-D1' );";
        String insert265 = "INSERT INTO " + location_table  + " VALUES(265, '02-008-E1' );";
        String insert266 = "INSERT INTO " + location_table  + " VALUES(266, '02-008-C2' );";
        String insert267 = "INSERT INTO " + location_table  + " VALUES(267, '02-008-D2' );";
        String insert268 = "INSERT INTO " + location_table  + " VALUES(268, '02-008-E2' );";
        String insert269 = "INSERT INTO " + location_table  + " VALUES(269, '02-010-C1' );";
        String insert270 = "INSERT INTO " + location_table  + " VALUES(270, '02-010-D1' );";
        String insert271 = "INSERT INTO " + location_table  + " VALUES(271, '02-010-E1' );";
        String insert272 = "INSERT INTO " + location_table  + " VALUES(272, '02-010-C2' );";
        String insert273 = "INSERT INTO " + location_table  + " VALUES(273, '02-010-D2' );";
        String insert274 = "INSERT INTO " + location_table  + " VALUES(274, '02-010-E2' );";
        String insert275 = "INSERT INTO " + location_table  + " VALUES(275, '02-012-C1' );";
        String insert276 = "INSERT INTO " + location_table  + " VALUES(276, '02-012-D1' );";
        String insert277 = "INSERT INTO " + location_table  + " VALUES(277, '02-012-E1' );";
        String insert278 = "INSERT INTO " + location_table  + " VALUES(278, '02-012-C2' );";
        String insert279 = "INSERT INTO " + location_table  + " VALUES(279, '02-012-D2' );";
        String insert280 = "INSERT INTO " + location_table  + " VALUES(280, '02-012-E2' );";
        String insert281 = "INSERT INTO " + location_table  + " VALUES(281, '02-014-C1' );";
        String insert282 = "INSERT INTO " + location_table  + " VALUES(282, '02-014-D1' );";
        String insert283 = "INSERT INTO " + location_table  + " VALUES(283, '02-014-E1' );";
        String insert284 = "INSERT INTO " + location_table  + " VALUES(284, '02-014-C2' );";
        String insert285 = "INSERT INTO " + location_table  + " VALUES(285, '02-014-D2' );";
        String insert286 = "INSERT INTO " + location_table  + " VALUES(286, '02-014-E2' );";
        String insert287 = "INSERT INTO " + location_table  + " VALUES(287, '02-016-C1' );";
        String insert288 = "INSERT INTO " + location_table  + " VALUES(288, '02-016-D1' );";
        String insert289 = "INSERT INTO " + location_table  + " VALUES(289, '02-016-E1' );";
        String insert290 = "INSERT INTO " + location_table  + " VALUES(290, '02-016-C2' );";
        String insert291 = "INSERT INTO " + location_table  + " VALUES(291, '02-016-D2' );";
        String insert292 = "INSERT INTO " + location_table  + " VALUES(292, '02-016-E2' );";
        String insert293 = "INSERT INTO " + location_table  + " VALUES(293, '02-018-C1' );";
        String insert294 = "INSERT INTO " + location_table  + " VALUES(294, '02-018-D1' );";
        String insert295 = "INSERT INTO " + location_table  + " VALUES(295, '02-018-E1' );";
        String insert296 = "INSERT INTO " + location_table  + " VALUES(296, '02-018-C2' );";
        String insert297 = "INSERT INTO " + location_table  + " VALUES(297, '02-018-D2' );";
        String insert298 = "INSERT INTO " + location_table  + " VALUES(298, '02-020-C1' );";
        String insert299 = "INSERT INTO " + location_table  + " VALUES(299, '02-020-D1' );";
        String insert300 = "INSERT INTO " + location_table  + " VALUES(300, '02-020-E1' );";
        String insert301 = "INSERT INTO " + location_table  + " VALUES(301, '02-020-F1' );";
        String insert302 = "INSERT INTO " + location_table  + " VALUES(302, '02-020-C2' );";
        String insert303 = "INSERT INTO " + location_table  + " VALUES(303, '02-020-D2' );";
        String insert304 = "INSERT INTO " + location_table  + " VALUES(304, '02-020-E2' );";
        String insert305 = "INSERT INTO " + location_table  + " VALUES(305, '02-020-F2' );";
        String insert306 = "INSERT INTO " + location_table  + " VALUES(306, '02-022-C1' );";
        String insert307 = "INSERT INTO " + location_table  + " VALUES(307, '02-022-D1' );";
        String insert308 = "INSERT INTO " + location_table  + " VALUES(308, '02-022-E1' );";
        String insert309 = "INSERT INTO " + location_table  + " VALUES(309, '02-022-C2' );";
        String insert310 = "INSERT INTO " + location_table  + " VALUES(310, '02-022-D2' );";
        String insert311 = "INSERT INTO " + location_table  + " VALUES(311, '02-022-E2' );";
        String insert312 = "INSERT INTO " + location_table  + " VALUES(312, '02-024-C1' );";
        String insert313 = "INSERT INTO " + location_table  + " VALUES(313, '02-024-D1' );";
        String insert314 = "INSERT INTO " + location_table  + " VALUES(314, '02-024-E1' );";
        String insert315 = "INSERT INTO " + location_table  + " VALUES(315, '02-024-C2' );";
        String insert316 = "INSERT INTO " + location_table  + " VALUES(316, '02-024-D2' );";
        String insert317 = "INSERT INTO " + location_table  + " VALUES(317, '02-024-E2' );";
        String insert318 = "INSERT INTO " + location_table  + " VALUES(318, '03-004-D1' );";
        String insert319 = "INSERT INTO " + location_table  + " VALUES(319, '03-004-E1' );";
        String insert320 = "INSERT INTO " + location_table  + " VALUES(320, '03-004-F1' );";
        String insert321 = "INSERT INTO " + location_table  + " VALUES(321, '03-004-D2' );";
        String insert322 = "INSERT INTO " + location_table  + " VALUES(322, '03-004-E2' );";
        String insert323 = "INSERT INTO " + location_table  + " VALUES(323, '03-004-F2' );";
        String insert324 = "INSERT INTO " + location_table  + " VALUES(324, '03-006-C1' );";
        String insert325 = "INSERT INTO " + location_table  + " VALUES(325, '03-006-D1' );";
        String insert326 = "INSERT INTO " + location_table  + " VALUES(326, '03-006-E1' );";
        String insert327 = "INSERT INTO " + location_table  + " VALUES(327, '03-006-F1' );";
        String insert328 = "INSERT INTO " + location_table  + " VALUES(328, '03-006-C2' );";
        String insert329 = "INSERT INTO " + location_table  + " VALUES(329, '03-006-D2' );";
        String insert330 = "INSERT INTO " + location_table  + " VALUES(330, '03-006-E2' );";
        String insert331 = "INSERT INTO " + location_table  + " VALUES(331, '03-006-F2' );";
        String insert332 = "INSERT INTO " + location_table  + " VALUES(332, '03-008-C1' );";
        String insert333 = "INSERT INTO " + location_table  + " VALUES(333, '03-008-D1' );";
        String insert334 = "INSERT INTO " + location_table  + " VALUES(334, '03-008-E1' );";
        String insert335 = "INSERT INTO " + location_table  + " VALUES(335, '03-008-F1' );";
        String insert336 = "INSERT INTO " + location_table  + " VALUES(336, '03-008-C2' );";
        String insert337 = "INSERT INTO " + location_table  + " VALUES(337, '03-008-D2' );";
        String insert338 = "INSERT INTO " + location_table  + " VALUES(338, '03-008-E2' );";
        String insert339 = "INSERT INTO " + location_table  + " VALUES(339, '03-008-F2' );";
        String insert340 = "INSERT INTO " + location_table  + " VALUES(340, '03-010-D1' );";
        String insert341 = "INSERT INTO " + location_table  + " VALUES(341, '03-010-E1' );";
        String insert342 = "INSERT INTO " + location_table  + " VALUES(342, '03-010-F1' );";
        String insert343 = "INSERT INTO " + location_table  + " VALUES(343, '03-010-D2' );";
        String insert344 = "INSERT INTO " + location_table  + " VALUES(344, '03-010-E2' );";
        String insert345 = "INSERT INTO " + location_table  + " VALUES(345, '03-010-F2' );";
        String insert346 = "INSERT INTO " + location_table  + " VALUES(346, '03-012-D1' );";
        String insert347 = "INSERT INTO " + location_table  + " VALUES(347, '03-012-E1' );";
        String insert348 = "INSERT INTO " + location_table  + " VALUES(348, '03-012-F1' );";
        String insert349 = "INSERT INTO " + location_table  + " VALUES(349, '03-012-D2' );";
        String insert350 = "INSERT INTO " + location_table  + " VALUES(350, '03-012-E2' );";
        String insert351 = "INSERT INTO " + location_table  + " VALUES(351, '03-012-F2' );";
        String insert352 = "INSERT INTO " + location_table  + " VALUES(352, '03-014-D1' );";
        String insert353 = "INSERT INTO " + location_table  + " VALUES(353, '03-014-E1' );";
        String insert354 = "INSERT INTO " + location_table  + " VALUES(354, '03-014-F1' );";
        String insert355 = "INSERT INTO " + location_table  + " VALUES(355, '03-014-D2' );";
        String insert356 = "INSERT INTO " + location_table  + " VALUES(356, '03-014-E2' );";
        String insert357 = "INSERT INTO " + location_table  + " VALUES(357, '03-014-F2' );";
        String insert358 = "INSERT INTO " + location_table  + " VALUES(358, '03-016-D1' );";
        String insert359 = "INSERT INTO " + location_table  + " VALUES(359, '03-016-E1' );";
        String insert360 = "INSERT INTO " + location_table  + " VALUES(360, '03-016-D2' );";
        String insert361 = "INSERT INTO " + location_table  + " VALUES(361, '03-016-E2' );";
        String insert362 = "INSERT INTO " + location_table  + " VALUES(362, '03-016-F2' );";
        String insert363 = "INSERT INTO " + location_table  + " VALUES(363, '03-018-D1' );";
        String insert364 = "INSERT INTO " + location_table  + " VALUES(364, '03-018-E1' );";
        String insert365 = "INSERT INTO " + location_table  + " VALUES(365, '03-018-F1' );";
        String insert366 = "INSERT INTO " + location_table  + " VALUES(366, '03-018-D2' );";
        String insert367 = "INSERT INTO " + location_table  + " VALUES(367, '03-018-E2' );";
        String insert368 = "INSERT INTO " + location_table  + " VALUES(368, '03-018-F2' );";
        String insert369 = "INSERT INTO " + location_table  + " VALUES(369, '03-020-D1' );";
        String insert370 = "INSERT INTO " + location_table  + " VALUES(370, '03-020-E1' );";
        String insert371 = "INSERT INTO " + location_table  + " VALUES(371, '03-020-F1' );";
        String insert372 = "INSERT INTO " + location_table  + " VALUES(372, '03-020-D2' );";
        String insert373 = "INSERT INTO " + location_table  + " VALUES(373, '03-020-E2' );";
        String insert374 = "INSERT INTO " + location_table  + " VALUES(374, '03-020-F2' );";
        String insert375 = "INSERT INTO " + location_table  + " VALUES(375, '03-022-D1' );";
        String insert376 = "INSERT INTO " + location_table  + " VALUES(376, '03-022-E1' );";
        String insert377 = "INSERT INTO " + location_table  + " VALUES(377, '03-022-F1' );";
        String insert378 = "INSERT INTO " + location_table  + " VALUES(378, '03-022-D2' );";
        String insert379 = "INSERT INTO " + location_table  + " VALUES(379, '03-022-E2' );";
        String insert380 = "INSERT INTO " + location_table  + " VALUES(380, '03-022-F2' );";
        String insert381 = "INSERT INTO " + location_table  + " VALUES(381, '03-024-E1' );";
        String insert382 = "INSERT INTO " + location_table  + " VALUES(382, '03-024-F1' );";
        String insert383 = "INSERT INTO " + location_table  + " VALUES(383, '03-024-G1' );";
        String insert384 = "INSERT INTO " + location_table  + " VALUES(384, '03-024-E2' );";
        String insert385 = "INSERT INTO " + location_table  + " VALUES(385, '03-024-F2' );";
        String insert386 = "INSERT INTO " + location_table  + " VALUES(386, '03-024-G2' );";
        String insert387 = "INSERT INTO " + location_table  + " VALUES(387, '03-026-E1' );";
        String insert388 = "INSERT INTO " + location_table  + " VALUES(388, '03-026-F1' );";
        String insert389 = "INSERT INTO " + location_table  + " VALUES(389, '03-026-G1' );";
        String insert390 = "INSERT INTO " + location_table  + " VALUES(390, '03-026-E2' );";
        String insert391 = "INSERT INTO " + location_table  + " VALUES(391, '03-026-F2' );";
        String insert392 = "INSERT INTO " + location_table  + " VALUES(392, '03-026-G2' );";
        String insert393 = "INSERT INTO " + location_table  + " VALUES(393, '03-003-E1' );";
        String insert394 = "INSERT INTO " + location_table  + " VALUES(394, '03-003-F1' );";
        String insert395 = "INSERT INTO " + location_table  + " VALUES(395, '03-003-E2' );";
        String insert396 = "INSERT INTO " + location_table  + " VALUES(396, '03-003-F2' );";
        String insert397 = "INSERT INTO " + location_table  + " VALUES(397, '03-005-E1' );";
        String insert398 = "INSERT INTO " + location_table  + " VALUES(398, '03-005-F1' );";
        String insert399 = "INSERT INTO " + location_table  + " VALUES(399, '03-007-F1' );";
        String insert400 = "INSERT INTO " + location_table  + " VALUES(400, '03-007-G1' );";
        String insert401 = "INSERT INTO " + location_table  + " VALUES(401, '03-007-F2' );";
        String insert402 = "INSERT INTO " + location_table  + " VALUES(402, '03-007-G2' );";
        String insert403 = "INSERT INTO " + location_table  + " VALUES(403, '03-009-E1' );";
        String insert404 = "INSERT INTO " + location_table  + " VALUES(404, '03-009-F1' );";
        String insert405 = "INSERT INTO " + location_table  + " VALUES(405, '03-009-E2' );";
        String insert406 = "INSERT INTO " + location_table  + " VALUES(406, '03-009-F2' );";
        String insert407 = "INSERT INTO " + location_table  + " VALUES(407, '03-011-E1' );";
        String insert408 = "INSERT INTO " + location_table  + " VALUES(408, '03-011-F1' );";
        String insert409 = "INSERT INTO " + location_table  + " VALUES(409, '03-011-G1' );";
        String insert410 = "INSERT INTO " + location_table  + " VALUES(410, '03-011-E2' );";
        String insert411 = "INSERT INTO " + location_table  + " VALUES(411, '03-011-F2' );";
        String insert412 = "INSERT INTO " + location_table  + " VALUES(412, '03-011-G2' );";
        String insert413 = "INSERT INTO " + location_table  + " VALUES(413, '03-013-E1' );";
        String insert414 = "INSERT INTO " + location_table  + " VALUES(414, '03-013-F1' );";
        String insert415 = "INSERT INTO " + location_table  + " VALUES(415, '03-013-E2' );";
        String insert416 = "INSERT INTO " + location_table  + " VALUES(416, '03-013-F2' );";
        String insert417 = "INSERT INTO " + location_table  + " VALUES(417, '03-015-E1' );";
        String insert418 = "INSERT INTO " + location_table  + " VALUES(418, '03-015-F1' );";
        String insert419 = "INSERT INTO " + location_table  + " VALUES(419, '03-015-E2' );";
        String insert420 = "INSERT INTO " + location_table  + " VALUES(420, '03-015-F2' );";
        String insert421 = "INSERT INTO " + location_table  + " VALUES(421, '03-017-E1' );";
        String insert422 = "INSERT INTO " + location_table  + " VALUES(422, '03-017-F1' );";
        String insert423 = "INSERT INTO " + location_table  + " VALUES(423, '03-017-E2' );";
        String insert424 = "INSERT INTO " + location_table  + " VALUES(424, '03-017-F2' );";
        String insert425 = "INSERT INTO " + location_table  + " VALUES(425, '03-019-D1' );";
        String insert426 = "INSERT INTO " + location_table  + " VALUES(426, '03-019-F1' );";
        String insert427 = "INSERT INTO " + location_table  + " VALUES(427, '03-019-G1' );";
        String insert428 = "INSERT INTO " + location_table  + " VALUES(428, '03-019-D2' );";
        String insert429 = "INSERT INTO " + location_table  + " VALUES(429, '03-019-F2' );";
        String insert430 = "INSERT INTO " + location_table  + " VALUES(430, '03-019-G2' );";
        String insert431 = "INSERT INTO " + location_table  + " VALUES(431, '03-021-F1' );";
        String insert432 = "INSERT INTO " + location_table  + " VALUES(432, '03-021-G1' );";
        String insert433 = "INSERT INTO " + location_table  + " VALUES(433, '03-021-F2' );";
        String insert434 = "INSERT INTO " + location_table  + " VALUES(434, '03-021-G2' );";
        String insert435 = "INSERT INTO " + location_table  + " VALUES(435, '03-023-F1' );";
        String insert436 = "INSERT INTO " + location_table  + " VALUES(436, '03-023-G1' );";
        String insert437 = "INSERT INTO " + location_table  + " VALUES(437, '03-023-F2' );";
        String insert438 = "INSERT INTO " + location_table  + " VALUES(438, '03-023-G2' );";
        String insert439 = "INSERT INTO " + location_table  + " VALUES(439, '03-025-F1' );";
        String insert440 = "INSERT INTO " + location_table  + " VALUES(440, '03-025-G1' );";
        String insert441 = "INSERT INTO " + location_table  + " VALUES(441, '03-025-F2' );";
        String insert442 = "INSERT INTO " + location_table  + " VALUES(442, '03-025-G2' );";
        String insert443 = "INSERT INTO " + location_table  + " VALUES(443, '04-001-E1' );";
        String insert444 = "INSERT INTO " + location_table  + " VALUES(444, '04-001-F1' );";
        String insert445 = "INSERT INTO " + location_table  + " VALUES(445, '04-001-G1' );";
        String insert446 = "INSERT INTO " + location_table  + " VALUES(446, '04-001-E2' );";
        String insert447 = "INSERT INTO " + location_table  + " VALUES(447, '04-001-F2' );";
        String insert448 = "INSERT INTO " + location_table  + " VALUES(448, '04-001-G2' );";
        String insert449 = "INSERT INTO " + location_table  + " VALUES(449, '04-003-D1' );";
        String insert450 = "INSERT INTO " + location_table  + " VALUES(450, '04-003-E1' );";
        String insert451 = "INSERT INTO " + location_table  + " VALUES(451, '04-003-F1' );";
        String insert452 = "INSERT INTO " + location_table  + " VALUES(452, '04-003-D2' );";
        String insert453 = "INSERT INTO " + location_table  + " VALUES(453, '04-003-E2' );";
        String insert454 = "INSERT INTO " + location_table  + " VALUES(454, '04-003-F2' );";
        String insert455 = "INSERT INTO " + location_table  + " VALUES(455, '04-005-D1' );";
        String insert456 = "INSERT INTO " + location_table  + " VALUES(456, '04-005-E1' );";
        String insert457 = "INSERT INTO " + location_table  + " VALUES(457, '04-005-F1' );";
        String insert458 = "INSERT INTO " + location_table  + " VALUES(458, '04-005-D2' );";
        String insert459 = "INSERT INTO " + location_table  + " VALUES(459, '04-005-E2' );";
        String insert460 = "INSERT INTO " + location_table  + " VALUES(460, '04-005-F2' );";
        String insert461 = "INSERT INTO " + location_table  + " VALUES(461, '04-007-D1' );";
        String insert462 = "INSERT INTO " + location_table  + " VALUES(462, '04-007-E1' );";
        String insert463 = "INSERT INTO " + location_table  + " VALUES(463, '04-007-F1' );";
        String insert464 = "INSERT INTO " + location_table  + " VALUES(464, '04-007-D2' );";
        String insert465 = "INSERT INTO " + location_table  + " VALUES(465, '04-007-E2' );";
        String insert466 = "INSERT INTO " + location_table  + " VALUES(466, '04-007-F2' );";
        String insert467 = "INSERT INTO " + location_table  + " VALUES(467, '04-009-E1' );";
        String insert468 = "INSERT INTO " + location_table  + " VALUES(468, '04-009-F1' );";
        String insert469 = "INSERT INTO " + location_table  + " VALUES(469, '04-009-G1' );";
        String insert470 = "INSERT INTO " + location_table  + " VALUES(470, '04-009-E2' );";
        String insert471 = "INSERT INTO " + location_table  + " VALUES(471, '04-009-F2' );";
        String insert472 = "INSERT INTO " + location_table  + " VALUES(472, '04-009-G2' );";
        String insert473 = "INSERT INTO " + location_table  + " VALUES(473, '04-011-E1' );";
        String insert474 = "INSERT INTO " + location_table  + " VALUES(474, '04-011-F1' );";
        String insert475 = "INSERT INTO " + location_table  + " VALUES(475, '04-011-G1' );";
        String insert476 = "INSERT INTO " + location_table  + " VALUES(476, '04-011-E2' );";
        String insert477 = "INSERT INTO " + location_table  + " VALUES(477, '04-011-F2' );";
        String insert478 = "INSERT INTO " + location_table  + " VALUES(478, '04-011-G2' );";
        String insert479 = "INSERT INTO " + location_table  + " VALUES(479, '04-013-E1' );";
        String insert480 = "INSERT INTO " + location_table  + " VALUES(480, '04-013-F1' );";
        String insert481 = "INSERT INTO " + location_table  + " VALUES(481, '04-013-G1' );";
        String insert482 = "INSERT INTO " + location_table  + " VALUES(482, '04-013-E2' );";
        String insert483 = "INSERT INTO " + location_table  + " VALUES(483, '04-013-F2' );";
        String insert484 = "INSERT INTO " + location_table  + " VALUES(484, '04-013-G2' );";
        String insert485 = "INSERT INTO " + location_table  + " VALUES(485, '04-015-E1' );";
        String insert486 = "INSERT INTO " + location_table  + " VALUES(486, '04-015-F1' );";
        String insert487 = "INSERT INTO " + location_table  + " VALUES(487, '04-015-G1' );";
        String insert488 = "INSERT INTO " + location_table  + " VALUES(488, '04-015-E2' );";
        String insert489 = "INSERT INTO " + location_table  + " VALUES(489, '04-015-F2' );";
        String insert490 = "INSERT INTO " + location_table  + " VALUES(490, '04-015-G2' );";
        String insert491 = "INSERT INTO " + location_table  + " VALUES(491, '04-017-E1' );";
        String insert492 = "INSERT INTO " + location_table  + " VALUES(492, '04-017-F1' );";
        String insert493 = "INSERT INTO " + location_table  + " VALUES(493, '04-017-G1' );";
        String insert494 = "INSERT INTO " + location_table  + " VALUES(494, '04-017-E2' );";
        String insert495 = "INSERT INTO " + location_table  + " VALUES(495, '04-017-F2' );";
        String insert496 = "INSERT INTO " + location_table  + " VALUES(496, '04-017-G2' );";
        String insert497 = "INSERT INTO " + location_table  + " VALUES(497, '04-019-E1' );";
        String insert498 = "INSERT INTO " + location_table  + " VALUES(498, '04-019-F1' );";
        String insert499 = "INSERT INTO " + location_table  + " VALUES(499, '04-019-G1' );";
        String insert500 = "INSERT INTO " + location_table  + " VALUES(500, '04-019-E2' );";
        String insert501 = "INSERT INTO " + location_table  + " VALUES(501, '04-019-F2' );";
        String insert502 = "INSERT INTO " + location_table  + " VALUES(502, '04-019-G2' );";
        String insert503 = "INSERT INTO " + location_table  + " VALUES(503, '04-021-E1' );";
        String insert504 = "INSERT INTO " + location_table  + " VALUES(504, '04-021-F1' );";
        String insert505 = "INSERT INTO " + location_table  + " VALUES(505, '04-021-G1' );";
        String insert506 = "INSERT INTO " + location_table  + " VALUES(506, '04-021-E2' );";
        String insert507 = "INSERT INTO " + location_table  + " VALUES(507, '04-021-F2' );";
        String insert508 = "INSERT INTO " + location_table  + " VALUES(508, '04-021-G2' );";
        String insert509 = "INSERT INTO " + location_table  + " VALUES(509, '04-023-E1' );";
        String insert510 = "INSERT INTO " + location_table  + " VALUES(510, '04-023-F1' );";
        String insert511 = "INSERT INTO " + location_table  + " VALUES(511, '04-023-G1' );";
        String insert512 = "INSERT INTO " + location_table  + " VALUES(512, '04-023-E2' );";
        String insert513 = "INSERT INTO " + location_table  + " VALUES(513, '04-023-F2' );";
        String insert514 = "INSERT INTO " + location_table  + " VALUES(514, '04-023-G2' );";
        String insert515 = "INSERT INTO " + location_table  + " VALUES(515, '04-002-C1' );";
        String insert516 = "INSERT INTO " + location_table  + " VALUES(516, '04-002-D1' );";
        String insert517 = "INSERT INTO " + location_table  + " VALUES(517, '04-002-E1' );";
        String insert518 = "INSERT INTO " + location_table  + " VALUES(518, '04-002-C2' );";
        String insert519 = "INSERT INTO " + location_table  + " VALUES(519, '04-002-D2' );";
        String insert520 = "INSERT INTO " + location_table  + " VALUES(520, '04-002-E2' );";
        String insert521 = "INSERT INTO " + location_table  + " VALUES(521, '04-004-C1' );";
        String insert522 = "INSERT INTO " + location_table  + " VALUES(522, '04-004-D1' );";
        String insert523 = "INSERT INTO " + location_table  + " VALUES(523, '04-004-E1' );";
        String insert524 = "INSERT INTO " + location_table  + " VALUES(524, '04-004-C2' );";
        String insert525 = "INSERT INTO " + location_table  + " VALUES(525, '04-004-D2' );";
        String insert526 = "INSERT INTO " + location_table  + " VALUES(526, '04-004-E2' );";
        String insert527 = "INSERT INTO " + location_table  + " VALUES(527, '04-006-C1' );";
        String insert528 = "INSERT INTO " + location_table  + " VALUES(528, '04-006-D1' );";
        String insert529 = "INSERT INTO " + location_table  + " VALUES(529, '04-006-E1' );";
        String insert530 = "INSERT INTO " + location_table  + " VALUES(530, '04-006-C2' );";
        String insert531 = "INSERT INTO " + location_table  + " VALUES(531, '04-006-D2' );";
        String insert532 = "INSERT INTO " + location_table  + " VALUES(532, '04-006-E2' );";
        String insert533 = "INSERT INTO " + location_table  + " VALUES(533, '04-008-C1' );";
        String insert534 = "INSERT INTO " + location_table  + " VALUES(534, '04-008-D1' );";
        String insert535 = "INSERT INTO " + location_table  + " VALUES(535, '04-008-E1' );";
        String insert536 = "INSERT INTO " + location_table  + " VALUES(536, '04-008-C2' );";
        String insert537 = "INSERT INTO " + location_table  + " VALUES(537, '04-008-D2' );";
        String insert538 = "INSERT INTO " + location_table  + " VALUES(538, '04-008-E2' );";
        String insert539 = "INSERT INTO " + location_table  + " VALUES(539, '04-010-C1' );";
        String insert540 = "INSERT INTO " + location_table  + " VALUES(540, '04-010-D1' );";
        String insert541 = "INSERT INTO " + location_table  + " VALUES(541, '04-010-E1' );";
        String insert542 = "INSERT INTO " + location_table  + " VALUES(542, '04-010-C2' );";
        String insert543 = "INSERT INTO " + location_table  + " VALUES(543, '04-010-D2' );";
        String insert544 = "INSERT INTO " + location_table  + " VALUES(544, '04-010-E2' );";
        String insert545 = "INSERT INTO " + location_table  + " VALUES(545, '04-012-C1' );";
        String insert546 = "INSERT INTO " + location_table  + " VALUES(546, '04-012-D1' );";
        String insert547 = "INSERT INTO " + location_table  + " VALUES(547, '04-012-E1' );";
        String insert548 = "INSERT INTO " + location_table  + " VALUES(548, '04-012-C2' );";
        String insert549 = "INSERT INTO " + location_table  + " VALUES(549, '04-012-D2' );";
        String insert550 = "INSERT INTO " + location_table  + " VALUES(550, '04-012-E2' );";
        String insert551 = "INSERT INTO " + location_table  + " VALUES(551, '04-014-C1' );";
        String insert552 = "INSERT INTO " + location_table  + " VALUES(552, '04-014-D1' );";
        String insert553 = "INSERT INTO " + location_table  + " VALUES(553, '04-014-E1' );";
        String insert554 = "INSERT INTO " + location_table  + " VALUES(554, '04-014-C2' );";
        String insert555 = "INSERT INTO " + location_table  + " VALUES(555, '04-014-D2' );";
        String insert556 = "INSERT INTO " + location_table  + " VALUES(556, '04-014-E2' );";
        String insert557 = "INSERT INTO " + location_table  + " VALUES(557, '04-016-C1' );";
        String insert558 = "INSERT INTO " + location_table  + " VALUES(558, '04-016-D1' );";
        String insert559 = "INSERT INTO " + location_table  + " VALUES(559, '04-016-E1' );";
        String insert560 = "INSERT INTO " + location_table  + " VALUES(560, '04-016-C2' );";
        String insert561 = "INSERT INTO " + location_table  + " VALUES(561, '04-016-D2' );";
        String insert562 = "INSERT INTO " + location_table  + " VALUES(562, '04-016-E2' );";
        String insert563 = "INSERT INTO " + location_table  + " VALUES(563, '04-018-C1' );";
        String insert564 = "INSERT INTO " + location_table  + " VALUES(564, '04-018-D1' );";
        String insert565 = "INSERT INTO " + location_table  + " VALUES(565, '04-018-E1' );";
        String insert566 = "INSERT INTO " + location_table  + " VALUES(566, '04-018-C2' );";
        String insert567 = "INSERT INTO " + location_table  + " VALUES(567, '04-018-D2' );";
        String insert568 = "INSERT INTO " + location_table  + " VALUES(568, '04-018-E2' );";
        String insert569 = "INSERT INTO " + location_table  + " VALUES(569, '04-020-C1' );";
        String insert570 = "INSERT INTO " + location_table  + " VALUES(570, '04-020-D1' );";
        String insert571 = "INSERT INTO " + location_table  + " VALUES(571, '04-020-E1' );";
        String insert572 = "INSERT INTO " + location_table  + " VALUES(572, '04-020-C2' );";
        String insert573 = "INSERT INTO " + location_table  + " VALUES(573, '04-020-D2' );";
        String insert574 = "INSERT INTO " + location_table  + " VALUES(574, '04-020-E2' );";
        String insert575 = "INSERT INTO " + location_table  + " VALUES(575, '04-022-D1' );";
        String insert576 = "INSERT INTO " + location_table  + " VALUES(576, '04-022-E1' );";
        String insert577 = "INSERT INTO " + location_table  + " VALUES(577, '04-022-F1' );";
        String insert578 = "INSERT INTO " + location_table  + " VALUES(578, '04-022-D2' );";
        String insert579 = "INSERT INTO " + location_table  + " VALUES(579, '04-022-E2' );";
        String insert580 = "INSERT INTO " + location_table  + " VALUES(580, '04-022-F2' );";
        String insert581 = "INSERT INTO " + location_table  + " VALUES(581, '04-024-D1' );";
        String insert582 = "INSERT INTO " + location_table  + " VALUES(582, '04-024-E1' );";
        String insert583 = "INSERT INTO " + location_table  + " VALUES(583, '04-024-D2' );";
        String insert584 = "INSERT INTO " + location_table  + " VALUES(584, '04-024-E2' );";
        String insert585 = "INSERT INTO " + location_table  + " VALUES(585, '04-024-F2' );";
        String insert586 = "INSERT INTO " + location_table  + " VALUES(586, '05-003-E1' );";
        String insert587 = "INSERT INTO " + location_table  + " VALUES(587, '05-003-F1' );";
        String insert588 = "INSERT INTO " + location_table  + " VALUES(588, '05-003-G1' );";
        String insert589 = "INSERT INTO " + location_table  + " VALUES(589, '05-003-E2' );";
        String insert590 = "INSERT INTO " + location_table  + " VALUES(590, '05-003-F2' );";
        String insert591 = "INSERT INTO " + location_table  + " VALUES(591, '05-003-G2' );";
        String insert592 = "INSERT INTO " + location_table  + " VALUES(592, '05-005-C1' );";
        String insert593 = "INSERT INTO " + location_table  + " VALUES(593, '05-005-D1' );";
        String insert594 = "INSERT INTO " + location_table  + " VALUES(594, '05-005-E1' );";
        String insert595 = "INSERT INTO " + location_table  + " VALUES(595, '05-005-C2' );";
        String insert596 = "INSERT INTO " + location_table  + " VALUES(596, '05-005-D2' );";
        String insert597 = "INSERT INTO " + location_table  + " VALUES(597, '05-005-E2' );";
        String insert598 = "INSERT INTO " + location_table  + " VALUES(598, '05-007-C1' );";
        String insert599 = "INSERT INTO " + location_table  + " VALUES(599, '05-007-D1' );";
        String insert600 = "INSERT INTO " + location_table  + " VALUES(600, '05-007-E1' );";
        String insert601 = "INSERT INTO " + location_table  + " VALUES(601, '05-007-C2' );";
        String insert602 = "INSERT INTO " + location_table  + " VALUES(602, '05-007-D2' );";
        String insert603 = "INSERT INTO " + location_table  + " VALUES(603, '05-007-E2' );";
        String insert604 = "INSERT INTO " + location_table  + " VALUES(604, '05-009-C1' );";
        String insert605 = "INSERT INTO " + location_table  + " VALUES(605, '05-009-D1' );";
        String insert606 = "INSERT INTO " + location_table  + " VALUES(606, '05-009-E1' );";
        String insert607 = "INSERT INTO " + location_table  + " VALUES(607, '05-009-C2' );";
        String insert608 = "INSERT INTO " + location_table  + " VALUES(608, '05-009-D2' );";
        String insert609 = "INSERT INTO " + location_table  + " VALUES(609, '05-009-E2' );";
        String insert610 = "INSERT INTO " + location_table  + " VALUES(610, '05-011-E1' );";
        String insert611 = "INSERT INTO " + location_table  + " VALUES(611, '05-011-F1' );";
        String insert612 = "INSERT INTO " + location_table  + " VALUES(612, '05-011-G1' );";
        String insert613 = "INSERT INTO " + location_table  + " VALUES(613, '05-011-E2' );";
        String insert614 = "INSERT INTO " + location_table  + " VALUES(614, '05-011-F2' );";
        String insert615 = "INSERT INTO " + location_table  + " VALUES(615, '05-011-G2' );";
        String insert616 = "INSERT INTO " + location_table  + " VALUES(616, '05-013-E1' );";
        String insert617 = "INSERT INTO " + location_table  + " VALUES(617, '05-013-F1' );";
        String insert618 = "INSERT INTO " + location_table  + " VALUES(618, '05-013-G1' );";
        String insert619 = "INSERT INTO " + location_table  + " VALUES(619, '05-013-E2' );";
        String insert620 = "INSERT INTO " + location_table  + " VALUES(620, '05-013-F2' );";
        String insert621 = "INSERT INTO " + location_table  + " VALUES(621, '05-013-G2' );";
        String insert622 = "INSERT INTO " + location_table  + " VALUES(622, '05-015-D1' );";
        String insert623 = "INSERT INTO " + location_table  + " VALUES(623, '05-015-E1' );";
        String insert624 = "INSERT INTO " + location_table  + " VALUES(624, '05-015-F1' );";
        String insert625 = "INSERT INTO " + location_table  + " VALUES(625, '05-015-D2' );";
        String insert626 = "INSERT INTO " + location_table  + " VALUES(626, '05-015-E2' );";
        String insert627 = "INSERT INTO " + location_table  + " VALUES(627, '05-015-F2' );";
        String insert628 = "INSERT INTO " + location_table  + " VALUES(628, '05-017-D1' );";
        String insert629 = "INSERT INTO " + location_table  + " VALUES(629, '05-017-E1' );";
        String insert630 = "INSERT INTO " + location_table  + " VALUES(630, '05-017-F1' );";
        String insert631 = "INSERT INTO " + location_table  + " VALUES(631, '05-017-D2' );";
        String insert632 = "INSERT INTO " + location_table  + " VALUES(632, '05-017-E2' );";
        String insert633 = "INSERT INTO " + location_table  + " VALUES(633, '05-017-F2' );";
        String insert634 = "INSERT INTO " + location_table  + " VALUES(634, '05-019-D1' );";
        String insert635 = "INSERT INTO " + location_table  + " VALUES(635, '05-019-E1' );";
        String insert636 = "INSERT INTO " + location_table  + " VALUES(636, '05-019-F1' );";
        String insert637 = "INSERT INTO " + location_table  + " VALUES(637, '05-019-D2' );";
        String insert638 = "INSERT INTO " + location_table  + " VALUES(638, '05-019-E2' );";
        String insert639 = "INSERT INTO " + location_table  + " VALUES(639, '05-019-F2' );";
        String insert640 = "INSERT INTO " + location_table  + " VALUES(640, '05-021-D1' );";
        String insert641 = "INSERT INTO " + location_table  + " VALUES(641, '05-021-E1' );";
        String insert642 = "INSERT INTO " + location_table  + " VALUES(642, '05-021-F1' );";
        String insert643 = "INSERT INTO " + location_table  + " VALUES(643, '05-021-D2' );";
        String insert644 = "INSERT INTO " + location_table  + " VALUES(644, '05-021-E2' );";
        String insert645 = "INSERT INTO " + location_table  + " VALUES(645, '05-021-F2' );";
        String insert646 = "INSERT INTO " + location_table  + " VALUES(646, '05-023-D1' );";
        String insert647 = "INSERT INTO " + location_table  + " VALUES(647, '05-023-E1' );";
        String insert648 = "INSERT INTO " + location_table  + " VALUES(648, '05-023-F1' );";
        String insert649 = "INSERT INTO " + location_table  + " VALUES(649, '05-023-D2' );";
        String insert650 = "INSERT INTO " + location_table  + " VALUES(650, '05-023-E2' );";
        String insert651 = "INSERT INTO " + location_table  + " VALUES(651, '05-023-F2' );";
        String insert652 = "INSERT INTO " + location_table  + " VALUES(652, '05-025-D1' );";
        String insert653 = "INSERT INTO " + location_table  + " VALUES(653, '05-025-E1' );";
        String insert654 = "INSERT INTO " + location_table  + " VALUES(654, '05-025-F1' );";
        String insert655 = "INSERT INTO " + location_table  + " VALUES(655, '05-025-D2' );";
        String insert656 = "INSERT INTO " + location_table  + " VALUES(656, '05-025-E2' );";
        String insert657 = "INSERT INTO " + location_table  + " VALUES(657, '05-025-F2' );";
        String insert658 = "INSERT INTO " + location_table  + " VALUES(658, '05-016-E1' );";
        String insert659 = "INSERT INTO " + location_table  + " VALUES(659, '05-016-F1' );";
        String insert660 = "INSERT INTO " + location_table  + " VALUES(660, '05-016-G1' );";
        String insert661 = "INSERT INTO " + location_table  + " VALUES(661, '05-016-E2' );";
        String insert662 = "INSERT INTO " + location_table  + " VALUES(662, '05-016-F2' );";
        String insert663 = "INSERT INTO " + location_table  + " VALUES(663, '05-016-G2' );";
        String insert664 = "INSERT INTO " + location_table  + " VALUES(664, '05-018-E1' );";
        String insert665 = "INSERT INTO " + location_table  + " VALUES(665, '05-018-F1' );";
        String insert666 = "INSERT INTO " + location_table  + " VALUES(666, '05-018-G1' );";
        String insert667 = "INSERT INTO " + location_table  + " VALUES(667, '05-018-E2' );";
        String insert668 = "INSERT INTO " + location_table  + " VALUES(668, '05-018-F2' );";
        String insert669 = "INSERT INTO " + location_table  + " VALUES(669, '05-018-G2' );";
        String insert670 = "INSERT INTO " + location_table  + " VALUES(670, '05-020-E1' );";
        String insert671 = "INSERT INTO " + location_table  + " VALUES(671, '05-020-F1' );";
        String insert672 = "INSERT INTO " + location_table  + " VALUES(672, '05-020-G1' );";
        String insert673 = "INSERT INTO " + location_table  + " VALUES(673, '05-020-E2' );";
        String insert674 = "INSERT INTO " + location_table  + " VALUES(674, '05-020-F2' );";
        String insert675 = "INSERT INTO " + location_table  + " VALUES(675, '05-020-G2' );";
        String insert676 = "INSERT INTO " + location_table  + " VALUES(676, '05-022-E1' );";
        String insert677 = "INSERT INTO " + location_table  + " VALUES(677, '05-022-F1' );";
        String insert678 = "INSERT INTO " + location_table  + " VALUES(678, '05-022-G1' );";
        String insert679 = "INSERT INTO " + location_table  + " VALUES(679, '05-022-E2' );";
        String insert680 = "INSERT INTO " + location_table  + " VALUES(680, '05-022-F2' );";
        String insert681 = "INSERT INTO " + location_table  + " VALUES(681, '05-022-G2' );";
        String insert682 = "INSERT INTO " + location_table  + " VALUES(682, '05-024-E1' );";
        String insert683 = "INSERT INTO " + location_table  + " VALUES(683, '05-024-F1' );";
        String insert684 = "INSERT INTO " + location_table  + " VALUES(684, '05-024-G1' );";
        String insert685 = "INSERT INTO " + location_table  + " VALUES(685, '05-024-E2' );";
        String insert686 = "INSERT INTO " + location_table  + " VALUES(686, '05-024-F2' );";
        String insert687 = "INSERT INTO " + location_table  + " VALUES(687, '05-024-G2' );";
        String insert688 = "INSERT INTO " + location_table  + " VALUES(688, '05-026-E1' );";
        String insert689 = "INSERT INTO " + location_table  + " VALUES(689, '05-026-F1' );";
        String insert690 = "INSERT INTO " + location_table  + " VALUES(690, '05-026-G1' );";
        String insert691 = "INSERT INTO " + location_table  + " VALUES(691, '05-026-E2' );";
        String insert692 = "INSERT INTO " + location_table  + " VALUES(692, '05-026-F2' );";
        String insert693 = "INSERT INTO " + location_table  + " VALUES(693, '05-026-G2' );";
        String insert694 = "INSERT INTO " + location_table  + " VALUES(694, '06-035-D1' );";
        String insert695 = "INSERT INTO " + location_table  + " VALUES(695, '06-035-E1' );";
        String insert696 = "INSERT INTO " + location_table  + " VALUES(696, '06-035-F1' );";
        String insert697 = "INSERT INTO " + location_table  + " VALUES(697, '06-035-D2' );";
        String insert698 = "INSERT INTO " + location_table  + " VALUES(698, '06-035-E2' );";
        String insert699 = "INSERT INTO " + location_table  + " VALUES(699, '06-035-F2' );";
        String insert700 = "INSERT INTO " + location_table  + " VALUES(700, '06-033-D1' );";
        String insert701 = "INSERT INTO " + location_table  + " VALUES(701, '06-033-E1' );";
        String insert702 = "INSERT INTO " + location_table  + " VALUES(702, '06-033-F1' );";
        String insert703 = "INSERT INTO " + location_table  + " VALUES(703, '06-033-D2' );";
        String insert704 = "INSERT INTO " + location_table  + " VALUES(704, '06-033-E2' );";
        String insert705 = "INSERT INTO " + location_table  + " VALUES(705, '06-033-F2' );";
        String insert706 = "INSERT INTO " + location_table  + " VALUES(706, '06-031-D1' );";
        String insert707 = "INSERT INTO " + location_table  + " VALUES(707, '06-031-E1' );";
        String insert708 = "INSERT INTO " + location_table  + " VALUES(708, '06-031-F1' );";
        String insert709 = "INSERT INTO " + location_table  + " VALUES(709, '06-031-D2' );";
        String insert710 = "INSERT INTO " + location_table  + " VALUES(710, '06-031-E2' );";
        String insert711 = "INSERT INTO " + location_table  + " VALUES(711, '06-031-F2' );";
        String insert712 = "INSERT INTO " + location_table  + " VALUES(712, '06-029-D1' );";
        String insert713 = "INSERT INTO " + location_table  + " VALUES(713, '06-029-E1' );";
        String insert714 = "INSERT INTO " + location_table  + " VALUES(714, '06-029-F1' );";
        String insert715 = "INSERT INTO " + location_table  + " VALUES(715, '06-029-D2' );";
        String insert716 = "INSERT INTO " + location_table  + " VALUES(716, '06-029-E2' );";
        String insert717 = "INSERT INTO " + location_table  + " VALUES(717, '06-029-F2' );";
        String insert718 = "INSERT INTO " + location_table  + " VALUES(718, '06-027-D1' );";
        String insert719 = "INSERT INTO " + location_table  + " VALUES(719, '06-027-E1' );";
        String insert720 = "INSERT INTO " + location_table  + " VALUES(720, '06-027-F1' );";
        String insert721 = "INSERT INTO " + location_table  + " VALUES(721, '06-027-D2' );";
        String insert722 = "INSERT INTO " + location_table  + " VALUES(722, '06-027-E2' );";
        String insert723 = "INSERT INTO " + location_table  + " VALUES(723, '06-027-F2' );";
        String insert724 = "INSERT INTO " + location_table  + " VALUES(724, '06-025-D1' );";
        String insert725 = "INSERT INTO " + location_table  + " VALUES(725, '06-025-E1' );";
        String insert726 = "INSERT INTO " + location_table  + " VALUES(726, '06-025-F1' );";
        String insert727 = "INSERT INTO " + location_table  + " VALUES(727, '06-025-D2' );";
        String insert728 = "INSERT INTO " + location_table  + " VALUES(728, '06-025-E2' );";
        String insert729 = "INSERT INTO " + location_table  + " VALUES(729, '06-025-F2' );";
        String insert730 = "INSERT INTO " + location_table  + " VALUES(730, '06-023-D1' );";
        String insert731 = "INSERT INTO " + location_table  + " VALUES(731, '06-023-E1' );";
        String insert732 = "INSERT INTO " + location_table  + " VALUES(732, '06-023-F1' );";
        String insert733 = "INSERT INTO " + location_table  + " VALUES(733, '06-023-D2' );";
        String insert734 = "INSERT INTO " + location_table  + " VALUES(734, '06-023-E2' );";
        String insert735 = "INSERT INTO " + location_table  + " VALUES(735, '06-023-F2' );";
        String insert736 = "INSERT INTO " + location_table  + " VALUES(736, '06-021-D1' );";
        String insert737 = "INSERT INTO " + location_table  + " VALUES(737, '06-021-E1' );";
        String insert738 = "INSERT INTO " + location_table  + " VALUES(738, '06-021-F1' );";
        String insert739 = "INSERT INTO " + location_table  + " VALUES(739, '06-021-D2' );";
        String insert740 = "INSERT INTO " + location_table  + " VALUES(740, '06-021-E2' );";
        String insert741 = "INSERT INTO " + location_table  + " VALUES(741, '06-021-F2' );";
        String insert742 = "INSERT INTO " + location_table  + " VALUES(742, '06-019-D1' );";
        String insert743 = "INSERT INTO " + location_table  + " VALUES(743, '06-019-E1' );";
        String insert744 = "INSERT INTO " + location_table  + " VALUES(744, '06-019-F1' );";
        String insert745 = "INSERT INTO " + location_table  + " VALUES(745, '06-019-D2' );";
        String insert746 = "INSERT INTO " + location_table  + " VALUES(746, '06-019-E2' );";
        String insert747 = "INSERT INTO " + location_table  + " VALUES(747, '06-019-F2' );";
        String insert748 = "INSERT INTO " + location_table  + " VALUES(748, '06-017-D1' );";
        String insert749 = "INSERT INTO " + location_table  + " VALUES(749, '06-017-E1' );";
        String insert750 = "INSERT INTO " + location_table  + " VALUES(750, '06-017-F1' );";
        String insert751 = "INSERT INTO " + location_table  + " VALUES(751, '06-017-D2' );";
        String insert752 = "INSERT INTO " + location_table  + " VALUES(752, '06-017-E2' );";
        String insert753 = "INSERT INTO " + location_table  + " VALUES(753, '06-017-F2' );";
        String insert754 = "INSERT INTO " + location_table  + " VALUES(754, '06-015-D1' );";
        String insert755 = "INSERT INTO " + location_table  + " VALUES(755, '06-015-E1' );";
        String insert756 = "INSERT INTO " + location_table  + " VALUES(756, '06-015-F1' );";
        String insert757 = "INSERT INTO " + location_table  + " VALUES(757, '06-015-D2' );";
        String insert758 = "INSERT INTO " + location_table  + " VALUES(758, '06-013-D1' );";
        String insert759 = "INSERT INTO " + location_table  + " VALUES(759, '06-013-E1' );";
        String insert760 = "INSERT INTO " + location_table  + " VALUES(760, '06-013-F1' );";
        String insert761 = "INSERT INTO " + location_table  + " VALUES(761, '06-013-D2' );";
        String insert762 = "INSERT INTO " + location_table  + " VALUES(762, '06-013-E2' );";
        String insert763 = "INSERT INTO " + location_table  + " VALUES(763, '06-013-F2' );";
        String insert764 = "INSERT INTO " + location_table  + " VALUES(764, '06-011-D1' );";
        String insert765 = "INSERT INTO " + location_table  + " VALUES(765, '06-011-E1' );";
        String insert766 = "INSERT INTO " + location_table  + " VALUES(766, '06-011-F1' );";
        String insert767 = "INSERT INTO " + location_table  + " VALUES(767, '06-011-D2' );";
        String insert768 = "INSERT INTO " + location_table  + " VALUES(768, '06-011-E2' );";
        String insert769 = "INSERT INTO " + location_table  + " VALUES(769, '06-011-F2' );";
        String insert770 = "INSERT INTO " + location_table  + " VALUES(770, '06-009-C1' );";
        String insert771 = "INSERT INTO " + location_table  + " VALUES(771, '06-009-D1' );";
        String insert772 = "INSERT INTO " + location_table  + " VALUES(772, '06-009-E1' );";
        String insert773 = "INSERT INTO " + location_table  + " VALUES(773, '06-009-C2' );";
        String insert774 = "INSERT INTO " + location_table  + " VALUES(774, '06-009-D2' );";
        String insert775 = "INSERT INTO " + location_table  + " VALUES(775, '06-009-E2' );";
        String insert776 = "INSERT INTO " + location_table  + " VALUES(776, '06-007-C1' );";
        String insert777 = "INSERT INTO " + location_table  + " VALUES(777, '06-007-D1' );";
        String insert778 = "INSERT INTO " + location_table  + " VALUES(778, '06-007-E1' );";
        String insert779 = "INSERT INTO " + location_table  + " VALUES(779, '06-007-C2' );";
        String insert780 = "INSERT INTO " + location_table  + " VALUES(780, '06-007-D2' );";
        String insert781 = "INSERT INTO " + location_table  + " VALUES(781, '06-007-E2' );";
        String insert782 = "INSERT INTO " + location_table  + " VALUES(782, '06-010-D1' );";
        String insert783 = "INSERT INTO " + location_table  + " VALUES(783, '06-010-E1' );";
        String insert784 = "INSERT INTO " + location_table  + " VALUES(784, '06-010-F1' );";
        String insert785 = "INSERT INTO " + location_table  + " VALUES(785, '06-010-D2' );";
        String insert786 = "INSERT INTO " + location_table  + " VALUES(786, '06-010-E2' );";
        String insert787 = "INSERT INTO " + location_table  + " VALUES(787, '06-010-F2' );";
        String insert788 = "INSERT INTO " + location_table  + " VALUES(788, '06-012-E1' );";
        String insert789 = "INSERT INTO " + location_table  + " VALUES(789, '06-012-F1' );";
        String insert790 = "INSERT INTO " + location_table  + " VALUES(790, '06-012-G1' );";
        String insert791 = "INSERT INTO " + location_table  + " VALUES(791, '06-012-E2' );";
        String insert792 = "INSERT INTO " + location_table  + " VALUES(792, '06-012-F2' );";
        String insert793 = "INSERT INTO " + location_table  + " VALUES(793, '06-012-G2' );";
        String insert794 = "INSERT INTO " + location_table  + " VALUES(794, '06-014-D1' );";
        String insert795 = "INSERT INTO " + location_table  + " VALUES(795, '06-014-E1' );";
        String insert796 = "INSERT INTO " + location_table  + " VALUES(796, '06-014-F1' );";
        String insert797 = "INSERT INTO " + location_table  + " VALUES(797, '06-014-D2' );";
        String insert798 = "INSERT INTO " + location_table  + " VALUES(798, '06-014-E2' );";
        String insert799 = "INSERT INTO " + location_table  + " VALUES(799, '06-014-F2' );";
        String insert800 = "INSERT INTO " + location_table  + " VALUES(800, '06-016-D1' );";
        String insert801 = "INSERT INTO " + location_table  + " VALUES(801, '06-016-E1' );";
        String insert802 = "INSERT INTO " + location_table  + " VALUES(802, '06-016-F1' );";
        String insert803 = "INSERT INTO " + location_table  + " VALUES(803, '06-016-D2' );";
        String insert804 = "INSERT INTO " + location_table  + " VALUES(804, '06-016-E2' );";
        String insert805 = "INSERT INTO " + location_table  + " VALUES(805, '06-016-F2' );";
        String insert806 = "INSERT INTO " + location_table  + " VALUES(806, '06-018-E1' );";
        String insert807 = "INSERT INTO " + location_table  + " VALUES(807, '06-018-F1' );";
        String insert808 = "INSERT INTO " + location_table  + " VALUES(808, '06-018-G1' );";
        String insert809 = "INSERT INTO " + location_table  + " VALUES(809, '06-018-E2' );";
        String insert810 = "INSERT INTO " + location_table  + " VALUES(810, '06-018-F2' );";
        String insert811 = "INSERT INTO " + location_table  + " VALUES(811, '06-018-G2' );";
        String insert812 = "INSERT INTO " + location_table  + " VALUES(812, '06-020-D1' );";
        String insert813 = "INSERT INTO " + location_table  + " VALUES(813, '06-020-E1' );";
        String insert814 = "INSERT INTO " + location_table  + " VALUES(814, '06-020-F1' );";
        String insert815 = "INSERT INTO " + location_table  + " VALUES(815, '06-020-D2' );";
        String insert816 = "INSERT INTO " + location_table  + " VALUES(816, '06-020-E2' );";
        String insert817 = "INSERT INTO " + location_table  + " VALUES(817, '06-020-F2' );";

        String SavedPalletTable = "INSERT INTO " + saved_pallet_id_table  + " VALUES(1, 1 );";






        db.execSQL(sizeInsert1);
        db.execSQL(sizeInsert2);
        db.execSQL(sizeInsert3);
        db.execSQL(sizeInsert4);
        db.execSQL(sizeInsert5);
        db.execSQL(sizeInsert6);
        db.execSQL(sizeInsert7);
        db.execSQL(sizeInsert8);


        db.execSQL(colorInsert1);
        db.execSQL(colorInsert2);
        db.execSQL(colorInsert3);
        db.execSQL(colorInsert4);
        db.execSQL(colorInsert5);
        db.execSQL(colorInsert6);
        db.execSQL(colorInsert7);
        db.execSQL(colorInsert8);
        db.execSQL(colorInsert9);
        db.execSQL(colorInsert10);
        db.execSQL(colorInsert11);
        db.execSQL(colorInsert12);
        db.execSQL(colorInsert13);
        db.execSQL(colorInsert14);
        db.execSQL(colorInsert15);
        db.execSQL(colorInsert16);
        db.execSQL(colorInsert17);
        db.execSQL(colorInsert18);
        db.execSQL(colorInsert19);
        db.execSQL(colorInsert20);
        db.execSQL(colorInsert21);
        db.execSQL(colorInsert22);
        db.execSQL(colorInsert23);
        db.execSQL(colorInsert24);
        db.execSQL(colorInsert25);
        db.execSQL(colorInsert26);
        db.execSQL(colorInsert27);
        db.execSQL(colorInsert28);
        db.execSQL(colorInsert29);
        db.execSQL(colorInsert30);
        db.execSQL(colorInsert31);
        db.execSQL(colorInsert32);
        db.execSQL(colorInsert33);
        db.execSQL(colorInsert34);
        db.execSQL(colorInsert35);
        db.execSQL(colorInsert36);
        db.execSQL(colorInsert37);
        db.execSQL(colorInsert38);
        db.execSQL(colorInsert39);
        db.execSQL(colorInsert40);
        db.execSQL(colorInsert41);
        db.execSQL(colorInsert42);
        db.execSQL(colorInsert43);
        db.execSQL(colorInsert44);
        db.execSQL(colorInsert45);
        db.execSQL(colorInsert46);
        db.execSQL(colorInsert47);
        db.execSQL(colorInsert48);
        db.execSQL(colorInsert49);
        db.execSQL(colorInsert50);
        db.execSQL(colorInsert51);
        db.execSQL(colorInsert52);
        db.execSQL(colorInsert53);
        db.execSQL(colorInsert54);
        db.execSQL(colorInsert55);
        db.execSQL(colorInsert56);
        db.execSQL(colorInsert57);
        db.execSQL(colorInsert58);
        db.execSQL(colorInsert59);
        db.execSQL(colorInsert60);
        db.execSQL(colorInsert61);
        db.execSQL(colorInsert62);
        db.execSQL(colorInsert63);
        db.execSQL(colorInsert64);
        db.execSQL(colorInsert65);
        db.execSQL(colorInsert66);
        db.execSQL(colorInsert67);
        db.execSQL(colorInsert68);
        db.execSQL(colorInsert69);
        db.execSQL(colorInsert70);
        db.execSQL(colorInsert71);
        db.execSQL(colorInsert72);
        db.execSQL(colorInsert73);
        db.execSQL(colorInsert74);
        db.execSQL(colorInsert75);
        db.execSQL(colorInsert76);
        db.execSQL(colorInsert77);
        db.execSQL(colorInsert78);
        db.execSQL(colorInsert79);
        db.execSQL(colorInsert80);
        db.execSQL(colorInsert81);
        db.execSQL(colorInsert82);
        db.execSQL(colorInsert83);
        db.execSQL(colorInsert84);
        db.execSQL(colorInsert85);
        db.execSQL(colorInsert86);
        db.execSQL(colorInsert87);
        db.execSQL(colorInsert88);
        db.execSQL(colorInsert89);
        db.execSQL(colorInsert90);
        db.execSQL(colorInsert91);
        db.execSQL(colorInsert92);
        db.execSQL(colorInsert93);
        db.execSQL(colorInsert94);
        db.execSQL(colorInsert95);
        db.execSQL(colorInsert96);
        db.execSQL(colorInsert97);
        db.execSQL(colorInsert98);
        db.execSQL(colorInsert99);
        db.execSQL(colorInsert100);
        db.execSQL(colorInsert101);
        db.execSQL(colorInsert102);
        db.execSQL(colorInsert103);
        db.execSQL(colorInsert104);
        db.execSQL(colorInsert105);
        db.execSQL(colorInsert106);
        db.execSQL(colorInsert107);
        db.execSQL(colorInsert108);
        db.execSQL(colorInsert109);
        db.execSQL(colorInsert110);
        db.execSQL(colorInsert111);
        db.execSQL(colorInsert112);
        db.execSQL(colorInsert113);
        db.execSQL(colorInsert114);
        db.execSQL(colorInsert115);
        db.execSQL(colorInsert116);
        db.execSQL(colorInsert117);
        db.execSQL(colorInsert118);
        db.execSQL(colorInsert119);
        db.execSQL(colorInsert120);
        db.execSQL(colorInsert121);
        db.execSQL(colorInsert122);
        db.execSQL(colorInsert123);
        db.execSQL(colorInsert124);
        db.execSQL(colorInsert125);
        db.execSQL(colorInsert126);
        db.execSQL(colorInsert127);
        db.execSQL(colorInsert128);
        db.execSQL(colorInsert129);
        db.execSQL(colorInsert130);
        db.execSQL(colorInsert131);
        db.execSQL(colorInsert132);
        db.execSQL(colorInsert133);
        db.execSQL(colorInsert134);
        db.execSQL(colorInsert135);
        db.execSQL(colorInsert136);
        db.execSQL(colorInsert137);
        db.execSQL(colorInsert138);
        db.execSQL(colorInsert139);
        db.execSQL(colorInsert140);
        db.execSQL(colorInsert141);
        db.execSQL(colorInsert142);
        db.execSQL(colorInsert143);
        db.execSQL(colorInsert144);
        db.execSQL(colorInsert145);
        db.execSQL(colorInsert146);
        db.execSQL(colorInsert147);
        db.execSQL(colorInsert148);
        db.execSQL(colorInsert149);





        db.execSQL(styleInsert1);
        db.execSQL(styleInsert2);
        db.execSQL(styleInsert3);
        db.execSQL(styleInsert4);
        db.execSQL(styleInsert5);
        db.execSQL(styleInsert6);
        db.execSQL(styleInsert7);
        db.execSQL(styleInsert8);
        db.execSQL(styleInsert9);
        db.execSQL(styleInsert10);
        db.execSQL(styleInsert11);
        db.execSQL(styleInsert12);
        db.execSQL(styleInsert13);
        db.execSQL(styleInsert14);
        db.execSQL(styleInsert15);
        db.execSQL(styleInsert16);
        db.execSQL(styleInsert17);
        db.execSQL(styleInsert18);
        db.execSQL(styleInsert19);
        db.execSQL(styleInsert20);
        db.execSQL(styleInsert21);
        db.execSQL(styleInsert22);
        db.execSQL(styleInsert23);
        db.execSQL(styleInsert24);
        db.execSQL(styleInsert25);
        db.execSQL(styleInsert26);
        db.execSQL(styleInsert27);
        db.execSQL(styleInsert28);
        db.execSQL(styleInsert29);
        db.execSQL(styleInsert30);
        db.execSQL(styleInsert31);
        db.execSQL(styleInsert32);
        db.execSQL(styleInsert33);
        db.execSQL(styleInsert34);
        db.execSQL(styleInsert35);
        db.execSQL(styleInsert36);
        db.execSQL(styleInsert37);
        db.execSQL(styleInsert38);
        db.execSQL(styleInsert39);
        db.execSQL(styleInsert40);
        db.execSQL(styleInsert41);
        db.execSQL(styleInsert42);
        db.execSQL(styleInsert43);
        db.execSQL(styleInsert44);
        db.execSQL(styleInsert45);
        db.execSQL(styleInsert46);
        db.execSQL(styleInsert47);
        db.execSQL(styleInsert48);
        db.execSQL(styleInsert49);
        db.execSQL(styleInsert50);
        db.execSQL(styleInsert51);
        db.execSQL(styleInsert52);
        db.execSQL(styleInsert53);
        db.execSQL(styleInsert54);
        db.execSQL(styleInsert55);
        db.execSQL(styleInsert56);
        db.execSQL(styleInsert57);
        db.execSQL(styleInsert58);
        db.execSQL(styleInsert59);
        db.execSQL(styleInsert60);
        db.execSQL(styleInsert61);
        db.execSQL(styleInsert62);
        db.execSQL(styleInsert63);
        db.execSQL(styleInsert64);
        db.execSQL(styleInsert65);
        db.execSQL(styleInsert66);
        db.execSQL(styleInsert67);
        db.execSQL(styleInsert68);
        db.execSQL(styleInsert69);
        db.execSQL(styleInsert70);
        db.execSQL(styleInsert71);
        db.execSQL(styleInsert72);
        db.execSQL(styleInsert73);
        db.execSQL(styleInsert74);
        db.execSQL(styleInsert75);
        db.execSQL(styleInsert76);
        db.execSQL(styleInsert77);
        db.execSQL(styleInsert78);
        db.execSQL(styleInsert79);
        db.execSQL(styleInsert80);
        db.execSQL(styleInsert81);
        db.execSQL(styleInsert82);
        db.execSQL(styleInsert83);
        db.execSQL(styleInsert84);
        db.execSQL(styleInsert85);
        db.execSQL(styleInsert86);
        db.execSQL(styleInsert87);
        db.execSQL(styleInsert88);
        db.execSQL(styleInsert89);
        db.execSQL(styleInsert90);
        db.execSQL(styleInsert91);
        db.execSQL(styleInsert92);
        db.execSQL(styleInsert93);
        db.execSQL(styleInsert94);
        db.execSQL(styleInsert95);
        db.execSQL(styleInsert96);
        db.execSQL(styleInsert97);
        db.execSQL(styleInsert98);
        db.execSQL(styleInsert99);
        db.execSQL(styleInsert100);
        db.execSQL(styleInsert101);
        db.execSQL(styleInsert102);
        db.execSQL(styleInsert103);
        db.execSQL(styleInsert104);
        db.execSQL(styleInsert105);
        db.execSQL(styleInsert106);
        db.execSQL(styleInsert107);
        db.execSQL(styleInsert108);
        db.execSQL(styleInsert109);
        db.execSQL(styleInsert110);
        db.execSQL(styleInsert111);
        db.execSQL(styleInsert112);
        db.execSQL(styleInsert113);
        db.execSQL(styleInsert114);
        db.execSQL(styleInsert115);
        db.execSQL(styleInsert116);
        db.execSQL(styleInsert117);
        db.execSQL(styleInsert118);
        db.execSQL(styleInsert119);
        db.execSQL(styleInsert120);
        db.execSQL(styleInsert121);
        db.execSQL(styleInsert122);
        db.execSQL(styleInsert123);
        db.execSQL(styleInsert124);
        db.execSQL(styleInsert125);
        db.execSQL(styleInsert126);
        db.execSQL(styleInsert127);
        db.execSQL(styleInsert128);
        db.execSQL(styleInsert129);
        db.execSQL(styleInsert130);
        db.execSQL(styleInsert131);
        db.execSQL(styleInsert132);
        db.execSQL(styleInsert133);
        db.execSQL(styleInsert134);
        db.execSQL(styleInsert135);
        db.execSQL(styleInsert136);
        db.execSQL(styleInsert137);
        db.execSQL(styleInsert138);
        db.execSQL(styleInsert139);
        db.execSQL(styleInsert140);
        db.execSQL(styleInsert141);
        db.execSQL(styleInsert142);
        db.execSQL(styleInsert143);
        db.execSQL(styleInsert144);
        db.execSQL(styleInsert145);
        db.execSQL(styleInsert146);
        db.execSQL(styleInsert147);
        db.execSQL(styleInsert148);
        db.execSQL(styleInsert149);
        db.execSQL(styleInsert150);
        db.execSQL(styleInsert151);
        db.execSQL(styleInsert152);
        db.execSQL(styleInsert153);
        db.execSQL(styleInsert154);
        db.execSQL(styleInsert155);
        db.execSQL(styleInsert156);
        db.execSQL(styleInsert157);
        db.execSQL(styleInsert158);
        db.execSQL(styleInsert159);
        db.execSQL(styleInsert160);
        db.execSQL(styleInsert161);
        db.execSQL(styleInsert162);
        db.execSQL(styleInsert163);
        db.execSQL(styleInsert164);
        db.execSQL(styleInsert165);
        db.execSQL(styleInsert166);
        db.execSQL(styleInsert167);
        db.execSQL(styleInsert168);
        db.execSQL(styleInsert169);
        db.execSQL(styleInsert170);
        db.execSQL(styleInsert171);
        db.execSQL(styleInsert172);
        db.execSQL(styleInsert173);
        db.execSQL(styleInsert174);
        db.execSQL(styleInsert175);
        db.execSQL(styleInsert176);
        db.execSQL(styleInsert177);
        db.execSQL(styleInsert178);
        db.execSQL(styleInsert179);
        db.execSQL(styleInsert180);
        db.execSQL(styleInsert181);
        db.execSQL(styleInsert182);
        db.execSQL(styleInsert183);
        db.execSQL(styleInsert184);
        db.execSQL(styleInsert185);
        db.execSQL(styleInsert186);
        db.execSQL(styleInsert187);
        db.execSQL(styleInsert188);
        db.execSQL(styleInsert189);
        db.execSQL(styleInsert190);
        db.execSQL(styleInsert191);
        db.execSQL(styleInsert192);
        db.execSQL(styleInsert193);
        db.execSQL(styleInsert194);
        db.execSQL(styleInsert195);
        db.execSQL(styleInsert196);
        db.execSQL(styleInsert197);
        db.execSQL(styleInsert198);
        db.execSQL(styleInsert199);
        db.execSQL(styleInsert200);
        db.execSQL(styleInsert201);
        db.execSQL(styleInsert202);
        db.execSQL(styleInsert203);
        db.execSQL(styleInsert204);
        db.execSQL(styleInsert205);
        db.execSQL(styleInsert206);
        db.execSQL(styleInsert207);
        db.execSQL(styleInsert208);
        db.execSQL(styleInsert209);
        db.execSQL(styleInsert210);
        db.execSQL(styleInsert211);
        db.execSQL(styleInsert212);
        db.execSQL(styleInsert213);
        db.execSQL(styleInsert214);
        db.execSQL(styleInsert215);
        db.execSQL(styleInsert216);
        db.execSQL(styleInsert217);
        db.execSQL(styleInsert218);
        db.execSQL(styleInsert219);
        db.execSQL(styleInsert220);
        db.execSQL(styleInsert221);
        db.execSQL(styleInsert222);
        db.execSQL(styleInsert223);
        db.execSQL(styleInsert224);
        db.execSQL(styleInsert225);
        db.execSQL(styleInsert226);
        db.execSQL(styleInsert227);
        db.execSQL(styleInsert228);
        db.execSQL(styleInsert229);
        db.execSQL(styleInsert230);
        db.execSQL(styleInsert231);
        db.execSQL(styleInsert232);
        db.execSQL(styleInsert233);
        db.execSQL(styleInsert234);
        db.execSQL(styleInsert235);
        db.execSQL(styleInsert236);
        db.execSQL(styleInsert237);
        db.execSQL(styleInsert238);
        db.execSQL(styleInsert239);
        db.execSQL(styleInsert240);
        db.execSQL(styleInsert241);
        db.execSQL(styleInsert242);
        db.execSQL(styleInsert243);
        db.execSQL(styleInsert244);
        db.execSQL(styleInsert245);
        db.execSQL(styleInsert246);
        db.execSQL(styleInsert247);
        db.execSQL(styleInsert248);
        db.execSQL(styleInsert249);
        db.execSQL(styleInsert250);



        db.execSQL(insert1);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);
        db.execSQL(insert6);
        db.execSQL(insert7);
        db.execSQL(insert8);
        db.execSQL(insert9);
        db.execSQL(insert10);
        db.execSQL(insert11);
        db.execSQL(insert12);
        db.execSQL(insert13);
        db.execSQL(insert14);
        db.execSQL(insert15);
        db.execSQL(insert16);
        db.execSQL(insert17);
        db.execSQL(insert18);
        db.execSQL(insert19);
        db.execSQL(insert20);
        db.execSQL(insert21);
        db.execSQL(insert22);
        db.execSQL(insert23);
        db.execSQL(insert24);
        db.execSQL(insert25);
        db.execSQL(insert26);
        db.execSQL(insert27);
        db.execSQL(insert28);
        db.execSQL(insert29);
        db.execSQL(insert30);
        db.execSQL(insert31);
        db.execSQL(insert32);
        db.execSQL(insert33);
        db.execSQL(insert34);
        db.execSQL(insert35);
        db.execSQL(insert36);
        db.execSQL(insert37);
        db.execSQL(insert38);
        db.execSQL(insert39);
        db.execSQL(insert40);
        db.execSQL(insert41);
        db.execSQL(insert42);
        db.execSQL(insert43);
        db.execSQL(insert44);
        db.execSQL(insert45);
        db.execSQL(insert46);
        db.execSQL(insert47);
        db.execSQL(insert48);
        db.execSQL(insert49);
        db.execSQL(insert50);
        db.execSQL(insert51);
        db.execSQL(insert52);
        db.execSQL(insert53);
        db.execSQL(insert54);
        db.execSQL(insert55);
        db.execSQL(insert56);
        db.execSQL(insert57);
        db.execSQL(insert58);
        db.execSQL(insert59);
        db.execSQL(insert60);
        db.execSQL(insert61);
        db.execSQL(insert62);
        db.execSQL(insert63);
        db.execSQL(insert64);
        db.execSQL(insert65);
        db.execSQL(insert66);
        db.execSQL(insert67);
        db.execSQL(insert68);
        db.execSQL(insert69);
        db.execSQL(insert70);
        db.execSQL(insert71);
        db.execSQL(insert72);
        db.execSQL(insert73);
        db.execSQL(insert74);
        db.execSQL(insert75);
        db.execSQL(insert76);
        db.execSQL(insert77);
        db.execSQL(insert78);
        db.execSQL(insert79);
        db.execSQL(insert80);
        db.execSQL(insert81);
        db.execSQL(insert82);
        db.execSQL(insert83);
        db.execSQL(insert84);
        db.execSQL(insert85);
        db.execSQL(insert86);
        db.execSQL(insert87);
        db.execSQL(insert88);
        db.execSQL(insert89);
        db.execSQL(insert90);
        db.execSQL(insert91);
        db.execSQL(insert92);
        db.execSQL(insert93);
        db.execSQL(insert94);
        db.execSQL(insert95);
        db.execSQL(insert96);
        db.execSQL(insert97);
        db.execSQL(insert98);
        db.execSQL(insert99);
        db.execSQL(insert100);
        db.execSQL(insert101);
        db.execSQL(insert102);
        db.execSQL(insert103);
        db.execSQL(insert104);
        db.execSQL(insert105);
        db.execSQL(insert106);
        db.execSQL(insert107);
        db.execSQL(insert108);
        db.execSQL(insert109);
        db.execSQL(insert110);
        db.execSQL(insert111);
        db.execSQL(insert112);
        db.execSQL(insert113);
        db.execSQL(insert114);
        db.execSQL(insert115);
        db.execSQL(insert116);
        db.execSQL(insert117);
        db.execSQL(insert118);
        db.execSQL(insert119);
        db.execSQL(insert120);
        db.execSQL(insert121);
        db.execSQL(insert122);
        db.execSQL(insert123);
        db.execSQL(insert124);
        db.execSQL(insert125);
        db.execSQL(insert126);
        db.execSQL(insert127);
        db.execSQL(insert128);
        db.execSQL(insert129);
        db.execSQL(insert130);
        db.execSQL(insert131);
        db.execSQL(insert132);
        db.execSQL(insert133);
        db.execSQL(insert134);
        db.execSQL(insert135);
        db.execSQL(insert136);
        db.execSQL(insert137);
        db.execSQL(insert138);
        db.execSQL(insert139);
        db.execSQL(insert140);
        db.execSQL(insert141);
        db.execSQL(insert142);
        db.execSQL(insert143);
        db.execSQL(insert144);
        db.execSQL(insert145);
        db.execSQL(insert146);
        db.execSQL(insert147);
        db.execSQL(insert148);
        db.execSQL(insert149);
        db.execSQL(insert150);
        db.execSQL(insert151);
        db.execSQL(insert152);
        db.execSQL(insert153);
        db.execSQL(insert154);
        db.execSQL(insert155);
        db.execSQL(insert156);
        db.execSQL(insert157);
        db.execSQL(insert158);
        db.execSQL(insert159);
        db.execSQL(insert160);
        db.execSQL(insert161);
        db.execSQL(insert162);
        db.execSQL(insert163);
        db.execSQL(insert164);
        db.execSQL(insert165);
        db.execSQL(insert166);
        db.execSQL(insert167);
        db.execSQL(insert168);
        db.execSQL(insert169);
        db.execSQL(insert170);
        db.execSQL(insert171);
        db.execSQL(insert172);
        db.execSQL(insert173);
        db.execSQL(insert174);
        db.execSQL(insert175);
        db.execSQL(insert176);
        db.execSQL(insert177);
        db.execSQL(insert178);
        db.execSQL(insert179);
        db.execSQL(insert180);
        db.execSQL(insert181);
        db.execSQL(insert182);
        db.execSQL(insert183);
        db.execSQL(insert184);
        db.execSQL(insert185);
        db.execSQL(insert186);
        db.execSQL(insert187);
        db.execSQL(insert188);
        db.execSQL(insert189);
        db.execSQL(insert190);
        db.execSQL(insert191);
        db.execSQL(insert192);
        db.execSQL(insert193);
        db.execSQL(insert194);
        db.execSQL(insert195);
        db.execSQL(insert196);
        db.execSQL(insert197);
        db.execSQL(insert198);
        db.execSQL(insert199);
        db.execSQL(insert200);
        db.execSQL(insert201);
        db.execSQL(insert202);
        db.execSQL(insert203);
        db.execSQL(insert204);
        db.execSQL(insert205);
        db.execSQL(insert206);
        db.execSQL(insert207);
        db.execSQL(insert208);
        db.execSQL(insert209);
        db.execSQL(insert210);
        db.execSQL(insert211);
        db.execSQL(insert212);
        db.execSQL(insert213);
        db.execSQL(insert214);
        db.execSQL(insert215);
        db.execSQL(insert216);
        db.execSQL(insert217);
        db.execSQL(insert218);
        db.execSQL(insert219);
        db.execSQL(insert220);
        db.execSQL(insert221);
        db.execSQL(insert222);
        db.execSQL(insert223);
        db.execSQL(insert224);
        db.execSQL(insert225);
        db.execSQL(insert226);
        db.execSQL(insert227);
        db.execSQL(insert228);
        db.execSQL(insert229);
        db.execSQL(insert230);
        db.execSQL(insert231);
        db.execSQL(insert232);
        db.execSQL(insert233);
        db.execSQL(insert234);
        db.execSQL(insert235);
        db.execSQL(insert236);
        db.execSQL(insert237);
        db.execSQL(insert238);
        db.execSQL(insert239);
        db.execSQL(insert240);
        db.execSQL(insert241);
        db.execSQL(insert242);
        db.execSQL(insert243);
        db.execSQL(insert244);
        db.execSQL(insert245);
        db.execSQL(insert246);
        db.execSQL(insert247);
        db.execSQL(insert248);
        db.execSQL(insert249);
        db.execSQL(insert250);
        db.execSQL(insert251);
        db.execSQL(insert252);
        db.execSQL(insert253);
        db.execSQL(insert254);
        db.execSQL(insert255);
        db.execSQL(insert256);
        db.execSQL(insert257);
        db.execSQL(insert258);
        db.execSQL(insert259);
        db.execSQL(insert260);
        db.execSQL(insert261);
        db.execSQL(insert262);
        db.execSQL(insert263);
        db.execSQL(insert264);
        db.execSQL(insert265);
        db.execSQL(insert266);
        db.execSQL(insert267);
        db.execSQL(insert268);
        db.execSQL(insert269);
        db.execSQL(insert270);
        db.execSQL(insert271);
        db.execSQL(insert272);
        db.execSQL(insert273);
        db.execSQL(insert274);
        db.execSQL(insert275);
        db.execSQL(insert276);
        db.execSQL(insert277);
        db.execSQL(insert278);
        db.execSQL(insert279);
        db.execSQL(insert280);
        db.execSQL(insert281);
        db.execSQL(insert282);
        db.execSQL(insert283);
        db.execSQL(insert284);
        db.execSQL(insert285);
        db.execSQL(insert286);
        db.execSQL(insert287);
        db.execSQL(insert288);
        db.execSQL(insert289);
        db.execSQL(insert290);
        db.execSQL(insert291);
        db.execSQL(insert292);
        db.execSQL(insert293);
        db.execSQL(insert294);
        db.execSQL(insert295);
        db.execSQL(insert296);
        db.execSQL(insert297);
        db.execSQL(insert298);
        db.execSQL(insert299);
        db.execSQL(insert300);
        db.execSQL(insert301);
        db.execSQL(insert302);
        db.execSQL(insert303);
        db.execSQL(insert304);
        db.execSQL(insert305);
        db.execSQL(insert306);
        db.execSQL(insert307);
        db.execSQL(insert308);
        db.execSQL(insert309);
        db.execSQL(insert310);
        db.execSQL(insert311);
        db.execSQL(insert312);
        db.execSQL(insert313);
        db.execSQL(insert314);
        db.execSQL(insert315);
        db.execSQL(insert316);
        db.execSQL(insert317);
        db.execSQL(insert318);
        db.execSQL(insert319);
        db.execSQL(insert320);
        db.execSQL(insert321);
        db.execSQL(insert322);
        db.execSQL(insert323);
        db.execSQL(insert324);
        db.execSQL(insert325);
        db.execSQL(insert326);
        db.execSQL(insert327);
        db.execSQL(insert328);
        db.execSQL(insert329);
        db.execSQL(insert330);
        db.execSQL(insert331);
        db.execSQL(insert332);
        db.execSQL(insert333);
        db.execSQL(insert334);
        db.execSQL(insert335);
        db.execSQL(insert336);
        db.execSQL(insert337);
        db.execSQL(insert338);
        db.execSQL(insert339);
        db.execSQL(insert340);
        db.execSQL(insert341);
        db.execSQL(insert342);
        db.execSQL(insert343);
        db.execSQL(insert344);
        db.execSQL(insert345);
        db.execSQL(insert346);
        db.execSQL(insert347);
        db.execSQL(insert348);
        db.execSQL(insert349);
        db.execSQL(insert350);
        db.execSQL(insert351);
        db.execSQL(insert352);
        db.execSQL(insert353);
        db.execSQL(insert354);
        db.execSQL(insert355);
        db.execSQL(insert356);
        db.execSQL(insert357);
        db.execSQL(insert358);
        db.execSQL(insert359);
        db.execSQL(insert360);
        db.execSQL(insert361);
        db.execSQL(insert362);
        db.execSQL(insert363);
        db.execSQL(insert364);
        db.execSQL(insert365);
        db.execSQL(insert366);
        db.execSQL(insert367);
        db.execSQL(insert368);
        db.execSQL(insert369);
        db.execSQL(insert370);
        db.execSQL(insert371);
        db.execSQL(insert372);
        db.execSQL(insert373);
        db.execSQL(insert374);
        db.execSQL(insert375);
        db.execSQL(insert376);
        db.execSQL(insert377);
        db.execSQL(insert378);
        db.execSQL(insert379);
        db.execSQL(insert380);
        db.execSQL(insert381);
        db.execSQL(insert382);
        db.execSQL(insert383);
        db.execSQL(insert384);
        db.execSQL(insert385);
        db.execSQL(insert386);
        db.execSQL(insert387);
        db.execSQL(insert388);
        db.execSQL(insert389);
        db.execSQL(insert390);
        db.execSQL(insert391);
        db.execSQL(insert392);
        db.execSQL(insert393);
        db.execSQL(insert394);
        db.execSQL(insert395);
        db.execSQL(insert396);
        db.execSQL(insert397);
        db.execSQL(insert398);
        db.execSQL(insert399);
        db.execSQL(insert400);
        db.execSQL(insert401);
        db.execSQL(insert402);
        db.execSQL(insert403);
        db.execSQL(insert404);
        db.execSQL(insert405);
        db.execSQL(insert406);
        db.execSQL(insert407);
        db.execSQL(insert408);
        db.execSQL(insert409);
        db.execSQL(insert410);
        db.execSQL(insert411);
        db.execSQL(insert412);
        db.execSQL(insert413);
        db.execSQL(insert414);
        db.execSQL(insert415);
        db.execSQL(insert416);
        db.execSQL(insert417);
        db.execSQL(insert418);
        db.execSQL(insert419);
        db.execSQL(insert420);
        db.execSQL(insert421);
        db.execSQL(insert422);
        db.execSQL(insert423);
        db.execSQL(insert424);
        db.execSQL(insert425);
        db.execSQL(insert426);
        db.execSQL(insert427);
        db.execSQL(insert428);
        db.execSQL(insert429);
        db.execSQL(insert430);
        db.execSQL(insert431);
        db.execSQL(insert432);
        db.execSQL(insert433);
        db.execSQL(insert434);
        db.execSQL(insert435);
        db.execSQL(insert436);
        db.execSQL(insert437);
        db.execSQL(insert438);
        db.execSQL(insert439);
        db.execSQL(insert440);
        db.execSQL(insert441);
        db.execSQL(insert442);
        db.execSQL(insert443);
        db.execSQL(insert444);
        db.execSQL(insert445);
        db.execSQL(insert446);
        db.execSQL(insert447);
        db.execSQL(insert448);
        db.execSQL(insert449);
        db.execSQL(insert450);
        db.execSQL(insert451);
        db.execSQL(insert452);
        db.execSQL(insert453);
        db.execSQL(insert454);
        db.execSQL(insert455);
        db.execSQL(insert456);
        db.execSQL(insert457);
        db.execSQL(insert458);
        db.execSQL(insert459);
        db.execSQL(insert460);
        db.execSQL(insert461);
        db.execSQL(insert462);
        db.execSQL(insert463);
        db.execSQL(insert464);
        db.execSQL(insert465);
        db.execSQL(insert466);
        db.execSQL(insert467);
        db.execSQL(insert468);
        db.execSQL(insert469);
        db.execSQL(insert470);
        db.execSQL(insert471);
        db.execSQL(insert472);
        db.execSQL(insert473);
        db.execSQL(insert474);
        db.execSQL(insert475);
        db.execSQL(insert476);
        db.execSQL(insert477);
        db.execSQL(insert478);
        db.execSQL(insert479);
        db.execSQL(insert480);
        db.execSQL(insert481);
        db.execSQL(insert482);
        db.execSQL(insert483);
        db.execSQL(insert484);
        db.execSQL(insert485);
        db.execSQL(insert486);
        db.execSQL(insert487);
        db.execSQL(insert488);
        db.execSQL(insert489);
        db.execSQL(insert490);
        db.execSQL(insert491);
        db.execSQL(insert492);
        db.execSQL(insert493);
        db.execSQL(insert494);
        db.execSQL(insert495);
        db.execSQL(insert496);
        db.execSQL(insert497);
        db.execSQL(insert498);
        db.execSQL(insert499);
        db.execSQL(insert500);
        db.execSQL(insert501);
        db.execSQL(insert502);
        db.execSQL(insert503);
        db.execSQL(insert504);
        db.execSQL(insert505);
        db.execSQL(insert506);
        db.execSQL(insert507);
        db.execSQL(insert508);
        db.execSQL(insert509);
        db.execSQL(insert510);
        db.execSQL(insert511);
        db.execSQL(insert512);
        db.execSQL(insert513);
        db.execSQL(insert514);
        db.execSQL(insert515);
        db.execSQL(insert516);
        db.execSQL(insert517);
        db.execSQL(insert518);
        db.execSQL(insert519);
        db.execSQL(insert520);
        db.execSQL(insert521);
        db.execSQL(insert522);
        db.execSQL(insert523);
        db.execSQL(insert524);
        db.execSQL(insert525);
        db.execSQL(insert526);
        db.execSQL(insert527);
        db.execSQL(insert528);
        db.execSQL(insert529);
        db.execSQL(insert530);
        db.execSQL(insert531);
        db.execSQL(insert532);
        db.execSQL(insert533);
        db.execSQL(insert534);
        db.execSQL(insert535);
        db.execSQL(insert536);
        db.execSQL(insert537);
        db.execSQL(insert538);
        db.execSQL(insert539);
        db.execSQL(insert540);
        db.execSQL(insert541);
        db.execSQL(insert542);
        db.execSQL(insert543);
        db.execSQL(insert544);
        db.execSQL(insert545);
        db.execSQL(insert546);
        db.execSQL(insert547);
        db.execSQL(insert548);
        db.execSQL(insert549);
        db.execSQL(insert550);
        db.execSQL(insert551);
        db.execSQL(insert552);
        db.execSQL(insert553);
        db.execSQL(insert554);
        db.execSQL(insert555);
        db.execSQL(insert556);
        db.execSQL(insert557);
        db.execSQL(insert558);
        db.execSQL(insert559);
        db.execSQL(insert560);
        db.execSQL(insert561);
        db.execSQL(insert562);
        db.execSQL(insert563);
        db.execSQL(insert564);
        db.execSQL(insert565);
        db.execSQL(insert566);
        db.execSQL(insert567);
        db.execSQL(insert568);
        db.execSQL(insert569);
        db.execSQL(insert570);
        db.execSQL(insert571);
        db.execSQL(insert572);
        db.execSQL(insert573);
        db.execSQL(insert574);
        db.execSQL(insert575);
        db.execSQL(insert576);
        db.execSQL(insert577);
        db.execSQL(insert578);
        db.execSQL(insert579);
        db.execSQL(insert580);
        db.execSQL(insert581);
        db.execSQL(insert582);
        db.execSQL(insert583);
        db.execSQL(insert584);
        db.execSQL(insert585);
        db.execSQL(insert586);
        db.execSQL(insert587);
        db.execSQL(insert588);
        db.execSQL(insert589);
        db.execSQL(insert590);
        db.execSQL(insert591);
        db.execSQL(insert592);
        db.execSQL(insert593);
        db.execSQL(insert594);
        db.execSQL(insert595);
        db.execSQL(insert596);
        db.execSQL(insert597);
        db.execSQL(insert598);
        db.execSQL(insert599);
        db.execSQL(insert600);
        db.execSQL(insert601);
        db.execSQL(insert602);
        db.execSQL(insert603);
        db.execSQL(insert604);
        db.execSQL(insert605);
        db.execSQL(insert606);
        db.execSQL(insert607);
        db.execSQL(insert608);
        db.execSQL(insert609);
        db.execSQL(insert610);
        db.execSQL(insert611);
        db.execSQL(insert612);
        db.execSQL(insert613);
        db.execSQL(insert614);
        db.execSQL(insert615);
        db.execSQL(insert616);
        db.execSQL(insert617);
        db.execSQL(insert618);
        db.execSQL(insert619);
        db.execSQL(insert620);
        db.execSQL(insert621);
        db.execSQL(insert622);
        db.execSQL(insert623);
        db.execSQL(insert624);
        db.execSQL(insert625);
        db.execSQL(insert626);
        db.execSQL(insert627);
        db.execSQL(insert628);
        db.execSQL(insert629);
        db.execSQL(insert630);
        db.execSQL(insert631);
        db.execSQL(insert632);
        db.execSQL(insert633);
        db.execSQL(insert634);
        db.execSQL(insert635);
        db.execSQL(insert636);
        db.execSQL(insert637);
        db.execSQL(insert638);
        db.execSQL(insert639);
        db.execSQL(insert640);
        db.execSQL(insert641);
        db.execSQL(insert642);
        db.execSQL(insert643);
        db.execSQL(insert644);
        db.execSQL(insert645);
        db.execSQL(insert646);
        db.execSQL(insert647);
        db.execSQL(insert648);
        db.execSQL(insert649);
        db.execSQL(insert650);
        db.execSQL(insert651);
        db.execSQL(insert652);
        db.execSQL(insert653);
        db.execSQL(insert654);
        db.execSQL(insert655);
        db.execSQL(insert656);
        db.execSQL(insert657);
        db.execSQL(insert658);
        db.execSQL(insert659);
        db.execSQL(insert660);
        db.execSQL(insert661);
        db.execSQL(insert662);
        db.execSQL(insert663);
        db.execSQL(insert664);
        db.execSQL(insert665);
        db.execSQL(insert666);
        db.execSQL(insert667);
        db.execSQL(insert668);
        db.execSQL(insert669);
        db.execSQL(insert670);
        db.execSQL(insert671);
        db.execSQL(insert672);
        db.execSQL(insert673);
        db.execSQL(insert674);
        db.execSQL(insert675);
        db.execSQL(insert676);
        db.execSQL(insert677);
        db.execSQL(insert678);
        db.execSQL(insert679);
        db.execSQL(insert680);
        db.execSQL(insert681);
        db.execSQL(insert682);
        db.execSQL(insert683);
        db.execSQL(insert684);
        db.execSQL(insert685);
        db.execSQL(insert686);
        db.execSQL(insert687);
        db.execSQL(insert688);
        db.execSQL(insert689);
        db.execSQL(insert690);
        db.execSQL(insert691);
        db.execSQL(insert692);
        db.execSQL(insert693);
        db.execSQL(insert694);
        db.execSQL(insert695);
        db.execSQL(insert696);
        db.execSQL(insert697);
        db.execSQL(insert698);
        db.execSQL(insert699);
        db.execSQL(insert700);
        db.execSQL(insert701);
        db.execSQL(insert702);
        db.execSQL(insert703);
        db.execSQL(insert704);
        db.execSQL(insert705);
        db.execSQL(insert706);
        db.execSQL(insert707);
        db.execSQL(insert708);
        db.execSQL(insert709);
        db.execSQL(insert710);
        db.execSQL(insert711);
        db.execSQL(insert712);
        db.execSQL(insert713);
        db.execSQL(insert714);
        db.execSQL(insert715);
        db.execSQL(insert716);
        db.execSQL(insert717);
        db.execSQL(insert718);
        db.execSQL(insert719);
        db.execSQL(insert720);
        db.execSQL(insert721);
        db.execSQL(insert722);
        db.execSQL(insert723);
        db.execSQL(insert724);
        db.execSQL(insert725);
        db.execSQL(insert726);
        db.execSQL(insert727);
        db.execSQL(insert728);
        db.execSQL(insert729);
        db.execSQL(insert730);
        db.execSQL(insert731);
        db.execSQL(insert732);
        db.execSQL(insert733);
        db.execSQL(insert734);
        db.execSQL(insert735);
        db.execSQL(insert736);
        db.execSQL(insert737);
        db.execSQL(insert738);
        db.execSQL(insert739);
        db.execSQL(insert740);
        db.execSQL(insert741);
        db.execSQL(insert742);
        db.execSQL(insert743);
        db.execSQL(insert744);
        db.execSQL(insert745);
        db.execSQL(insert746);
        db.execSQL(insert747);
        db.execSQL(insert748);
        db.execSQL(insert749);
        db.execSQL(insert750);
        db.execSQL(insert751);
        db.execSQL(insert752);
        db.execSQL(insert753);
        db.execSQL(insert754);
        db.execSQL(insert755);
        db.execSQL(insert756);
        db.execSQL(insert757);
        db.execSQL(insert758);
        db.execSQL(insert759);
        db.execSQL(insert760);
        db.execSQL(insert761);
        db.execSQL(insert762);
        db.execSQL(insert763);
        db.execSQL(insert764);
        db.execSQL(insert765);
        db.execSQL(insert766);
        db.execSQL(insert767);
        db.execSQL(insert768);
        db.execSQL(insert769);
        db.execSQL(insert770);
        db.execSQL(insert771);
        db.execSQL(insert772);
        db.execSQL(insert773);
        db.execSQL(insert774);
        db.execSQL(insert775);
        db.execSQL(insert776);
        db.execSQL(insert777);
        db.execSQL(insert778);
        db.execSQL(insert779);
        db.execSQL(insert780);
        db.execSQL(insert781);
        db.execSQL(insert782);
        db.execSQL(insert783);
        db.execSQL(insert784);
        db.execSQL(insert785);
        db.execSQL(insert786);
        db.execSQL(insert787);
        db.execSQL(insert788);
        db.execSQL(insert789);
        db.execSQL(insert790);
        db.execSQL(insert791);
        db.execSQL(insert792);
        db.execSQL(insert793);
        db.execSQL(insert794);
        db.execSQL(insert795);
        db.execSQL(insert796);
        db.execSQL(insert797);
        db.execSQL(insert798);
        db.execSQL(insert799);
        db.execSQL(insert800);
        db.execSQL(insert801);
        db.execSQL(insert802);
        db.execSQL(insert803);
        db.execSQL(insert804);
        db.execSQL(insert805);
        db.execSQL(insert806);
        db.execSQL(insert807);
        db.execSQL(insert808);
        db.execSQL(insert809);
        db.execSQL(insert810);
        db.execSQL(insert811);
        db.execSQL(insert812);
        db.execSQL(insert813);
        db.execSQL(insert814);
        db.execSQL(insert815);
        db.execSQL(insert816);
        db.execSQL(insert817);

        db.execSQL(SavedPalletTable);

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // this is called if the database version number changes. It prevents previous user apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean addSize (SizeModel sizeModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(size_id, sizeModel.getSize_id());
        cv.put(size, sizeModel.getSize());

        long insert = db.insert(size_table, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean addStyle (StyleModel styleModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(style_id, styleModel.getStyle_id());
        cv.put(style_code, styleModel.getStyle());


        long insert = db.insert(style_table, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean addLocation (LocationModel locationModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(location_id, locationModel.getLocation_id());
        cv.put(location, locationModel.getLocation());


        long insert = db.insert(location_table, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean addColor(ColorModel colorModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(color_id, colorModel.getColor_id());
        cv.put(color, colorModel.getColor());


        long insert = db.insert(color_table, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean addPallet(PalletModel palletModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(pallet_location, palletModel.getLocation());


        long insert = db.insert(pallet_table, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean addPalletStyle(PalletStylesModel palletStylesModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(style_pallet_id, palletStylesModel.getStyle_pallet_id());
        cv.put(style_code, palletStylesModel.getStyleCode());
        cv.put(style_color, palletStylesModel.getStyleColor());
        cv.put(style_size, palletStylesModel.getStyleSize());
        cv.put(quantity, palletStylesModel.getQuantity());


        long insert = db.insert(pallet_styles_table, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<StyleSearchByLocationDTO> getPalletStylesByLocation(String location) {

        List<StyleSearchByLocationDTO> returnSearchList = new ArrayList<>();

        String queryString = "SELECT "

                + style_pallet_id
                + ", "
                + style_code
                + ", "
                + style_color
                + ", "
                + style_size
                + ", "
                + quantity
                + ", "
                + pallet_location
                + ", "
                + pallet_styles_id
                + " FROM "
                + pallet_table
                + " INNER JOIN "
                + pallet_styles_table
                + " ON "
                + pallet_styles_table
                + ". "
                + style_pallet_id
                + " = "
                + pallet_table
                + ". "
                + pallet_id
                + " WHERE " + pallet_location + " LIKE '%" + location + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        StyleSearchDTO newTop = null;
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int style_pallet_id = cursor.getInt(0);
                String style_code = cursor.getString(1);
                String style_color = cursor.getString(2);
                String style_size = cursor.getString(3);
                int quantity = cursor.getInt(4);
                String pallet_location = cursor.getString(5);
                int pallet_styles_id = cursor.getInt(6);

                StyleSearchByLocationDTO newSearchStyle = new StyleSearchByLocationDTO(style_pallet_id, style_code, style_color, style_size, quantity, pallet_location, pallet_styles_id);
                returnSearchList.add(newSearchStyle);

            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything to the list.
        }


        cursor.close();
        db.close();
        return returnSearchList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<String> getStylesSearchcsv(String searchTerm) {

        ArrayList<String> returnSearchList = new ArrayList<>();

        String queryString = "SELECT "
                + style_code
                + ", "
                + style_color
                + ", "
                + style_size
                + ", "
                + quantity
                + ", "
                + pallet_location
                + " FROM "
                + pallet_table
                + " INNER JOIN "
                + pallet_styles_table
                + " ON "
                + pallet_styles_table
                + ". "
                + style_pallet_id
                + " = "
                + pallet_table
                + ". "
                + pallet_id
                + " WHERE " + style_code + " LIKE '%" + searchTerm + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        StyleSearchDTO newTop = null;
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                String style_code = cursor.getString(0);
                String style_color = cursor.getString(1);
                String style_size = cursor.getString(2);
                int quantity = cursor.getInt(3);
                String pallet_location = cursor.getString(4);

                String newSearchStyle = "\n" + style_code + "#" + style_color + "#" + style_size + "#" + quantity + "#" + pallet_location + "@";
                returnSearchList.add(newSearchStyle);
//                newTop = new StyleSearchDTO("Style Code", "style_color", "style_size", 0, "pallet_location");

            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything to the list.
        }


        cursor.close();
        db.close();
//        returnSearchList.add(0, newTop);
        return returnSearchList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<StyleSearchDTO> getStylesSearch(String searchTerm) {

        List<StyleSearchDTO> returnSearchList = new ArrayList<>();

        String queryString = "SELECT "
                + style_code
                + ", "
                + style_color
                + ", "
                + style_size
                + ", "
                + quantity
                + ", "
                + pallet_location
                + " FROM "
                + pallet_table
                + " INNER JOIN "
                + pallet_styles_table
                + " ON "
                + pallet_styles_table
                + ". "
                + style_pallet_id
                + " = "
                + pallet_table
                + ". "
                + pallet_id
                + " WHERE " + style_code + " LIKE '%" + searchTerm + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        StyleSearchDTO newTop = null;
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                String style_code = cursor.getString(0);
                String style_color = cursor.getString(1);
                String style_size = cursor.getString(2);
                int quantity = cursor.getInt(3);
                String pallet_location = cursor.getString(4);

                StyleSearchDTO newSearchStyle = new StyleSearchDTO(style_code, style_color, style_size, quantity, pallet_location);
                returnSearchList.add(newSearchStyle);
//                newTop = new StyleSearchDTO("Style Code", "style_color", "style_size", 0, "pallet_location");

            } while (cursor.moveToNext());
        } else {
            // failure. do not add anything to the list.
        }


        cursor.close();
        db.close();
//        returnSearchList.add(0, newTop);
        return returnSearchList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<SizeModel> getAllSizes(){

        List<SizeModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + size_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int size_id = cursor.getInt(0);
                String location = cursor.getString(1);

                SizeModel newSize = new SizeModel(size_id, location);
                returnList.add(newSize);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<StyleModel> getAllStyles(){

        List<StyleModel> returnStyleList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + style_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int style_id = cursor.getInt(0);
                String style = cursor.getString(1);

                StyleModel newStyle = new StyleModel(style_id, style);
                returnStyleList.add(newStyle);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnStyleList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<LocationModel> getAllLocations(){

        ArrayList<LocationModel> returnLocationList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + location_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int location_id = cursor.getInt(0);
                String location = cursor.getString(1);

                LocationModel newLocation = new LocationModel(location_id, location);
                returnLocationList.add(newLocation);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnLocationList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<ColorModel> getAllColors(){

        List<ColorModel> returnColorList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + color_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int color_id = cursor.getInt(0);
                String color = cursor.getString(1);

                ColorModel newColor = new ColorModel(color_id, color);
                returnColorList.add(newColor);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnColorList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<PalletStylesModel> getAllPalletStyles(){

        List<PalletStylesModel> returnPalletStylesList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + pallet_styles_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int pallet_styles_id = cursor.getInt(0);
                int style_pallet_id = cursor.getInt(1);
                String style_code = cursor.getString(2);
                String style_color= cursor.getString(3);
                String style_size = cursor.getString(4);
                int quantity = cursor.getInt(5);

                PalletStylesModel newPalletStyle = new PalletStylesModel(pallet_styles_id, style_pallet_id, style_code, style_color, style_size, quantity);
                returnPalletStylesList.add(newPalletStyle);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnPalletStylesList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<PalletModel> getAllPallets(){

        List<PalletModel> returnPalletList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + pallet_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new objects. Put them into the return list.
            do {
                int pallet_id = cursor.getInt(0);
                String pallet_location = cursor.getString(1);

                PalletModel newPallet = new PalletModel(pallet_id, pallet_location);
                returnPalletList.add(newPallet);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnPalletList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("Range")
    public ArrayList<String> getAllSizesA(){

        ArrayList<String> returnArrayList = new ArrayList<>();

        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(size_table,null,null,null,null,null, null);

        cursor.moveToFirst();
            // loop through the cursor (result set) and create new objects. Put them into the return list.
           while (!cursor.isAfterLast()) {
               returnArrayList.add(cursor.getString(cursor.getColumnIndex(size)));
               cursor.moveToNext();
           }

        cursor.close();

        return returnArrayList;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("Range")
    public ArrayList<String> getAllStylesA(){

        ArrayList<String> returnStyleArrayList = new ArrayList<>();

        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(style_table,null,null,null,null,null, null);

        cursor.moveToFirst();
        // loop through the cursor (result set) and create new objects. Put them into the return list.
        while (!cursor.isAfterLast()) {
            returnStyleArrayList.add(cursor.getString(cursor.getColumnIndex(style_code)));
            cursor.moveToNext();
        }

        cursor.close();

        return returnStyleArrayList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("Range")
    public ArrayList<String> getAllLocationsA(){

        ArrayList<String> returnLocationArrayList = new ArrayList<>();

        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(location_table,null,null,null,null,null, null);

        cursor.moveToFirst();
        // loop through the cursor (result set) and create new objects. Put them into the return list.
        while (!cursor.isAfterLast()) {
            returnLocationArrayList.add(cursor.getString(cursor.getColumnIndex(location)));
            cursor.moveToNext();
        }

        cursor.close();

        return returnLocationArrayList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("Range")
    public ArrayList<String> getAllColorsA(){

        ArrayList<String> returnColorArrayList = new ArrayList<>();

        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(color_table,null,null,null,null,null, null);

        cursor.moveToFirst();
        // loop through the cursor (result set) and create new objects. Put them into the return list.
        while (!cursor.isAfterLast()) {
            returnColorArrayList.add(cursor.getString(cursor.getColumnIndex(color)));
            cursor.moveToNext();
        }

        cursor.close();

        return returnColorArrayList;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllPalletsA(){

        ArrayList<String> returnPalletArrayList = new ArrayList<>();

        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(pallet_table,null,null,null,null,null, null);

        cursor.moveToFirst();
        // loop through the cursor (result set) and create new objects. Put them into the return list.
        while (!cursor.isAfterLast()) {
            returnPalletArrayList.add(cursor.getString(cursor.getColumnIndex(pallet_location)));
            cursor.moveToNext();
        }

        cursor.close();

        return returnPalletArrayList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean deletelocation (LocationModel locationModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + location_table + " WHERE " + location_id + " = " + locationModel.getLocation_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePallet (PalletModel palletModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + pallet_table + " WHERE " + pallet_id + " = " + palletModel.getPallet_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteSize (SizeModel sizeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + size_table + " WHERE " + size_id + " = " + sizeModel.getSize_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStyle (StyleModel styleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + style_table + " WHERE " + style_id + " = " + styleModel.getStyle_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteColor (ColorModel colorModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + color_table + " WHERE " + color_id + " = " + colorModel.getColor_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStyleFromPallet (StyleSearchByLocationDTO styleSearchByLocationDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + pallet_styles_table + " WHERE " + pallet_styles_id + " = " + styleSearchByLocationDTO.getPallet_styles_id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
    @SuppressLint("Range")
    public int getSavedPalletID(){

        int palletId = 0;

        // get data from the database

        String queryString = "SELECT " + saved_pallet + " FROM " + saved_pallet_id_table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
         palletId = cursor.getInt(cursor.getColumnIndex(saved_pallet));
        }
        else {
            palletId = 99999999;
        }

        cursor.close();
        db.close();
        return palletId;
    }

    public void updatePalletId (int palletId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(saved_pallet, palletId);


        long in = db.update(saved_pallet_id_table, cv,"saved_pallet_id = 1",null);
        if (in == -1) {
        }
        else {
        }
    }
    public void removePallet (PalletModel palletModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(pallet_location, "REMOVED " + palletModel.getPallet_id());
        cv.put(is_removed, Boolean.TRUE);


        long in = db.update(pallet_table, cv,"pallet_id = " +  palletModel.getPallet_id(),null);
        if (in == -1) {
        }
        else {
        }
    }

    public void reassignLocation (PalletModel palletModel, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(pallet_location, location);
        cv.put(is_removed, Boolean.FALSE);


        long in = db.update(pallet_table, cv,"pallet_id = " +  palletModel.getPallet_id(),null);
        if (in == -1) {
        }
        else {
        }
    }
}
