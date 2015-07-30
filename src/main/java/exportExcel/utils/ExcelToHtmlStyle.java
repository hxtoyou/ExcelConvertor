package exportExcel.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import exportExcel.entity.HtmlRowStructure;
import exportExcel.entity.HtmlStructure;
import exportExcel.entity.HtmlTdStructure;


/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
public class ExcelToHtmlStyle {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 根据表格合并情况设置整个表格每行的行数和列数，表格的合成情况在htmlTDstructure表中的tag标志位来表示是否被合并
	 * 
	 * @param addresses
	 *            所有合并项
	 * @param headers
	 *            表格头行数
	 * @param details
	 *            表格详细数据行数
	 * @param tails
	 *            表格统计栏行数
	 * @param row
	 *            表格总行数
	 * @param cells
	 *            表格总列行数
	 */
	public HtmlStructure createByRegin(List<CellRangeAddress> addresses, List<Integer> headers, List<Integer> details,
			List<Integer> tails, Integer row, Integer cell) {
		HtmlStructure htmlStructure = new HtmlStructure();
		htmlStructure.setRowNum(row);
		htmlStructure.setColumn(cell);
		// int head = headers.size();
		// int tail = tails.size();
		// int detail = details.size();
		// Map<String, String> rowNum = new HashMap<String, String>();
		// 初始化html表的数据结构
		List<HtmlRowStructure> htmlRowStructures = Lists.newArrayList();
		for (int k = 0; k <= row; k++) {
			HtmlRowStructure htmlStructureTemp = new HtmlRowStructure();
			List<HtmlTdStructure> htmlTdStructures = Lists.newArrayList();
			for (int h = 0; h < cell; h++) {
				HtmlTdStructure htmlTdStructureTemp = new HtmlTdStructure();
				htmlTdStructures.add(htmlTdStructureTemp);
			}
			htmlStructureTemp.setHtmlTdStructure(htmlTdStructures);
			htmlStructureTemp.setCellsNum(cell);
			htmlRowStructures.add(htmlStructureTemp);
		}
		// 标记被合并的单元格，计算每一行有多少个单元格
		for (int i = 0; i <= row; i++) {

			HtmlRowStructure htmlRowStructure = htmlRowStructures.get(i);
			int cellNum = htmlRowStructure.getCellsNum();
			for (CellRangeAddress address : addresses) {
				int firstColumn = address.getFirstColumn();
				int lastColumn = address.getLastColumn();
				int firstRow = address.getFirstRow();
				int lastRow = address.getLastRow();
				int reginRow = lastRow - firstRow;
				int reginCell = lastColumn - firstColumn;
				if (firstRow > i) {
					continue;
				} else if (firstRow == i) {
					if (reginCell > 0) {
						cellNum -= reginCell;
						if (reginCell > 0) {
							for (int y = 1; y <= reginCell; y++) {
								htmlRowStructures.get(i).getHtmlTdStructure().get(firstColumn + y).setTag(true);
								logger.debug("cellNUm:{}", firstColumn + y);
								logger.debug("htmlRowStructure:{}",
										htmlRowStructures.get(i).getHtmlTdStructure().get(firstColumn + y).getTag());
							}
						}
					}
					// 但出现合并行的时候，下一行需要生成的表格最好减一，再加上合并的列数并且把被合并的单元格标记为true
					if (reginRow > 0) {
						for (int j = 1; j <= reginRow; j++) {
							int otherRowCellnum = htmlRowStructures.get(i + j).getCellsNum() - 1 - reginCell;
							htmlRowStructures.get(i + j).setCellsNum(otherRowCellnum);
							htmlRowStructures.get(i + j).getHtmlTdStructure().get(firstColumn).setTag(true);
							if (reginCell > 0) {
								for (int x = 1; x <= reginCell; x++) {
									htmlRowStructures.get(i + j).getHtmlTdStructure().get(firstColumn + x).setTag(true);
								}
							}
							// if (firstRow == (row - head) && reginRow == (row
							// - head - tail)) {
							//
							// }
						}
					}
				} else {

				}
			}
			htmlRowStructure.setRowNum(i);
			htmlRowStructure.setCellsNum(cellNum);
			htmlRowStructures.set(i, htmlRowStructure);
		}
		htmlStructure.setHtmlRowStructure(htmlRowStructures);
		return htmlStructure;
	}

	/**
	 * 删除被标记的单元格
	 * 
	 * @param htmlStructure
	 * @param addresses
	 * @param headers
	 * @param details
	 * @param tails
	 * @param row
	 * @param cell
	 * @return
	 */
	public HtmlStructure tableStyle(HtmlStructure htmlStructure, List<CellRangeAddress> addresses,
			List<Integer> headers, List<Integer> details, int trueRowNum, Integer row, Integer cell) {
		List<HtmlRowStructure> htmlRowStructures = htmlStructure.getHtmlRowStructure();
		Map<Integer, String> data = new HashMap<Integer, String>();
		// List<String> tailNum = Arrays.asList(tails.split(","));
		// int tailsize = Integer.parseInt(tailNum.get(tailNum.size()));
		// for (CellRangeAddress address : addresses) {
		// int firstRow = address.getFirstRow();
		// if (data.get(firstRow) != null) {
		//
		// data.put(firstRow, data.get(firstRow) + ";" +
		// address.getFirstColumn());
		// } else {
		// data.put(firstRow, String.valueOf(address.getFirstColumn()));
		// }
		// }
		// 对合并过的单元格设置colspan和rowspan
		for (CellRangeAddress address : addresses) {
			int firstColumn = address.getFirstColumn();
			int lastColumn = address.getLastColumn();
			int firstRow = address.getFirstRow();
			int lastRow = address.getLastRow();
			int reginRow = lastRow - firstRow;
			int reginCell = lastColumn - firstColumn;
			List<HtmlTdStructure> tdStructures = htmlRowStructures.get(firstRow).getHtmlTdStructure();
			// int delTdNum = 0;
			// String[] columns = data.get(firstRow).split(";");
			// int reginNum = 0;
			// for (String column : columns) {
			// if (Integer.parseInt(column) < firstColumn) {
			// reginNum += 1;
			// }
			// }
			// for (int i = 0; i < firstColumn; i++) {
			// if (tdStructures.get(i).getTag() != null) {
			// delTdNum += 1;
			// }
			// }
			if (reginRow > 0) {
				tdStructures.get(firstColumn).setRowspan(reginRow + 1);
			}
			if (reginCell > 0) {
				tdStructures.get(firstColumn).setColspan(reginCell + 1);
			}
		}
		// 根据表尾行数，删除剩余的row
		int tailsize = trueRowNum + 1;
		logger.debug("RowSIZE:{}", htmlRowStructures.size());
		int rowSize = htmlRowStructures.size();
		for (int delRow = tailsize; delRow < rowSize; delRow++) {
			htmlRowStructures.remove(tailsize);
		}
		// 删除被标记为被合并的单元格，由于一次遍历删除不掉某一些单元格，遍历两次。待优化
		for (HtmlRowStructure htmlRowStructure : htmlRowStructures) {
			List<HtmlTdStructure> htmlTdStructures = htmlRowStructure.getHtmlTdStructure();
			// if (htmlRowStructure.getHtmlTdStructure() == null) {
			// htmlRowStructures.remove(htmlRowStructure);
			// }
			// for (int i = 0; i < htmlTdStructures.size(); i++) {
			// if (htmlTdStructures.get(i).getTag() != null) {
			// if (htmlTdStructures.get(i).getTag() == true) {
			// htmlTdStructures.remove(i);
			// }
			// }
			// }
			// for (HtmlTdStructure removeTd : htmlTdStructures) {
			// if (removeTd.getTag() != null) {
			// htmlTdStructures.remove(removeTd);
			// }
			// }
			Iterator<HtmlTdStructure> tdIter = htmlTdStructures.iterator();
			while (tdIter.hasNext()) {
				HtmlTdStructure td = tdIter.next();
				if (td.getTag() != null) {
					tdIter.remove();// 这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
				}
			}
		}
		// for (HtmlRowStructure htmlRowStructure : htmlRowStructures) {
		// List<HtmlTdStructure> htmlTdStructures =
		// htmlRowStructure.getHtmlTdStructure();
		// for (int i = 0; i < htmlTdStructures.size(); i++) {
		// if (htmlTdStructures.get(i).getTag() != null) {
		// htmlTdStructures.remove(i);
		// }
		// }
		// }
		return htmlStructure;
	}
}
