package excel.test;

import excel.util.log;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestReadExcel {

    private static Sheet excelWSheet1;
    private static Workbook excelWBook1;
    private static Cell cell1;
    private static Row row1;
    private static FileInputStream in;
    private static FileOutputStream out;

    //指定要操作的excel文件的路径及sheet名称
    public static void setExcelFile(String path, String sheetName) throws Exception{
        Properties properties =new Properties();
        try{
            FileInputStream in = new FileInputStream(path);
            properties.load(in);
            in.close();
        }catch(IOException e){
            log.info("读取文件对象出错。");
            e.printStackTrace();
        }
        //声明一个file文件对象
        File file = new File(path);
        //创建一个输入流
        in = new FileInputStream(file);
        //声明workbook对象
        excelWBook1 = null;
        //判断文件扩展名
        String fileExtensionName = path.substring(path.indexOf("."));
        if(fileExtensionName.equals(".xlsx")){
            excelWBook1 = new XSSFWorkbook(in);
        }else {
            excelWBook1 = new HSSFWorkbook(in);
        }
        //获取sheet对象
        excelWSheet1 = excelWBook1.getSheet(sheetName);
    }

    //读取Excel数据
    public static Object[][] readExcel()  {
        Object[][] results = null;
        try{
            //获取sheet中数据的行数,行号从0始
            int rowCount = excelWSheet1.getLastRowNum()-excelWSheet1.getFirstRowNum();
            List<Object[]> records = new ArrayList<Object[]>();
            //读取数据（省略第一行表头）
            for(int i = 1; i < rowCount+1; i++){
                //获取行对象
                row1 = excelWSheet1.getRow(i);
                //声明一个数组存每行的测试数据,excel最后两列不需传值
                String[] fields = new String[row1.getLastCellNum()-1];
                //excel倒数第二列为Y，表示数据行要被测试脚本执行，否则不执行
                if(row1.getCell(row1.getLastCellNum()-1).getStringCellValue().equals("女")){
                    for(int j = 0; j < row1.getLastCellNum()-1; j++){
                        //判断单元格数据是数字还是字符(都转为字符串)
                        if (row1.getCell(j).getCellTypeEnum() == (CellType.STRING)){
                            fields[j] = row1.getCell(j).getStringCellValue();
                        }else {
                            fields[j] = "" + row1.getCell(j).getNumericCellValue();
                        }
                        //fields[j] = row.getCell(j).getCellTypeEnum() == CellType.STRING ? row.getCell(j).getStringCellValue() : ""+row.getCell(j).getNumericCellValue();
                        //使用下面这行代码会报错不知怎么解决，如果哪位知道解决方法求告知
                        //fields[j] = row.getCell(j).getCellType() == CellType.STRING ? row.getCell(j).getStringCellValue() : ""+row.getCell(j).getNumericCellValue();
                    }
                    records.add(fields);
                }
            }
            //将list转为Object二维数据
            results = new Object[records.size()][];
            //设置二维数据每行的值，每行是一个object对象
            for(int w=0; w < records.size(); w++){
                results[w] = records.get(w);
            }
            //in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    //读取excel文件指定单元格数据
    public static Object getCellData(int rowNum, int colNum) {
        try {
            //获取指定单元格对象
            cell1 = excelWSheet1.getRow(rowNum).getCell(colNum);
            Object cellData = null;
            //获取单元格的内容
            if (cell1.getCellTypeEnum() == CellType.STRING){
                cellData = cell1.getStringCellValue();
            }else if (cell1.getCellTypeEnum() == CellType.BLANK){
                cellData = null;
            }else if (cell1.getCellTypeEnum() == CellType.BOOLEAN){
                cellData = cell1.getBooleanCellValue();
            }else if (cell1.getCellTypeEnum() == CellType.FORMULA){
                cellData = cell1.getCellFormula();
            }else if (cell1.getCellTypeEnum() == CellType.NUMERIC){
                cellData = cell1.getNumericCellValue();
            }
            return cellData;
        } catch (Exception e) {
            return "读取数值错误！！！";
        }
    }

/*    //删除excel文件指定单元格数据
    public static void remoteCellData(int rowNum, int colNum, String path) throws Exception {
        row1 = excelWSheet1.getRow(rowNum);
        cell1 = row1.getCell(colNum);
        if (cell1 != null) {
            row1.removeCell(cell1);
        }
        cell1 = row1.createCell(colNum);
        out = new FileOutputStream(path);
        out.flush();
    }*/

    //在EXCEL的执行单元格中写入数据(此方法只针对.xlsx后辍的Excel文件) rowNum 行号，colNum 列号
    public static void setCellData(int rowNum, int colNum, String Result, String path) {
        try {
            //获取行对象
            row1 = excelWSheet1.getRow(rowNum);
            //如果单元格为空，则返回null
            cell1 = row1.getCell(colNum);
            if(cell1 == null){
                cell1=row1.createCell(colNum);
                cell1.setCellValue(Result);
            }else{
                cell1.setCellValue(Result);
            }
            out = new FileOutputStream(path);
            //将内容写入excel中
            excelWBook1.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //返回数据文件最后一列的列号，如果有12列则返回11
    public static int getLastColumnNum(){
        return excelWSheet1.getRow(0).getLastCellNum()-1;
    }

    //遍历打印excel数据
    public static void outPutExcelData(Object[][] sheet2s) {
        for (int i = 0; i < sheet2s.length; i++) {
            Object[] sheet2 = sheet2s[i];
            for (int j = 0; j < sheet2.length; j++) {
                System.out.println(sheet2[j]);
            }
        }
    }
}
