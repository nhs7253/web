package Reader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vo.BookVo;

public class ExcelReader {
	private List<BookVo> list = null;
	private FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private int rowindex=0;
	private int columnindex=0;
	private XSSFSheet sheet=null;
	private int rows=0;

	
	public ExcelReader() throws IOException{
		list = new ArrayList<>();
		fis = new FileInputStream("booklist1000.xlsx");
		workbook=new XSSFWorkbook(fis);
		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		sheet=workbook.getSheetAt(0);
		//행의 수
		rows=sheet.getPhysicalNumberOfRows();
	}
	
	public ExcelReader(String bookList) throws IOException{
		list = new ArrayList<>();
		fis = new FileInputStream(bookList);
		workbook=new XSSFWorkbook(fis);
		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		sheet=workbook.getSheetAt(0);
		//행의 수
		rows=sheet.getPhysicalNumberOfRows();
	}
	
	public List<BookVo> read(){
		Object[] data = null;
		for(rowindex=1;rowindex<rows;rowindex++){
		    //행을읽는다
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        
		        data = new Object[5];
		        
		        for(columnindex=0;columnindex<=cells;columnindex++){
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(columnindex);
		            String value="";
		            //셀이 빈값일경우를 위한 널체크
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
		                switch (cell.getCellType()){
		                case XSSFCell.CELL_TYPE_FORMULA:
		                    value=cell.getCellFormula();
		                    data[columnindex] = value;
		                    break;
		                case XSSFCell.CELL_TYPE_NUMERIC:
		                    value=cell.getNumericCellValue()+"";
		                    data[columnindex] = value;
		                    break;
		                case XSSFCell.CELL_TYPE_STRING:
		                    value=cell.getStringCellValue()+"";
		                    data[columnindex] = value;
		                    break;
		                case XSSFCell.CELL_TYPE_BLANK:
		                    value=cell.getBooleanCellValue()+"";
		                    data[columnindex] = value;
		                    break;
		                case XSSFCell.CELL_TYPE_ERROR:
		                    value=cell.getErrorCellValue()+"";
		                    data[columnindex] = value;
		                    break;
		                }
		            }
//		            System.out.print(value+"\t");
		        }
//		        System.out.println();
		        
		        String day = null;
		        if(!(data[4] == null || data[4].equals(""))){
		        	day = (data[4]+"").replace(".0", "").replace("[","").replace("]", "");
		        }else{
		        	day = null;
		        }
		        list.add(new BookVo(data[0]+"", data[1]+"", data[2]+"", data[3]+"", day));
		    }
		}
		System.out.println("읽기 완료");
		return list;
	}
}//클래스 끝
