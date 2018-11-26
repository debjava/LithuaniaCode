package com.dds.deba.custom.page;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * This is a utility class for pagination.
 * @author Debadatta Mishra
 *
 */
public final class PaginationProcessor 
{
	/**
	 * Default constructor.
	 */
	public PaginationProcessor()
	{
		super();
	}

	/**
	 * This method is used to obtain the list of data
	 * for pagination.
	 * @param request of type {@link HttpServletRequest}
	 * @return returns the list of data.
	 * @author Debadatta Mishra(PIKU)
	 */
	public List getProcessedList( HttpServletRequest request )
	{
		List pageItemsDataList = new ArrayList();
		/*
		 * The following indicates the total number of records.
		 */
		String totalNoRecords = request.getParameter("inputText");
		request.setAttribute("inputText", totalNoRecords);
		/*
		 * The following indicates the total no of items to display.
		 */
		int totalItems = Integer.parseInt(totalNoRecords);
		//It indicates the size of the page to display the items
		int pageSize = 10;
		int pageGroupSize = 10;
		int defaultPageCursorPosition = 3;
		/*
		 * The following indicates the current page which user
		 * has selected.
		 */
		String sCurrentPage = request.getParameter("pageNos");
		int currentPage = (sCurrentPage != null ? Integer.parseInt(sCurrentPage) : 0);
		request.setAttribute("pageNos", currentPage);
		/*
		 * For the purpose of pagination, it is necessary to know the
		 * start index and the end index.
		 */
		int startIndex = currentPage * pageSize;
		int endIndex = startIndex + pageSize;
		List pageList = getList(totalItems, currentPage,
				pageSize, pageGroupSize, defaultPageCursorPosition);
		pageItemsDataList = getDBDataList(startIndex, endIndex); 
		int totalPages = getTotalPages(totalItems, pageSize);
		/*
		 * Store all the following values in the request
		 */
		request.setAttribute("itemPg", currentPage);
		request.setAttribute("pageItems", pageItemsDataList);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("defaultPageCursorPosition", defaultPageCursorPosition);
		request.setAttribute("pageList", pageList);
		return pageItemsDataList;
	}

	/**
	 * This is a generic method to obtain the total number of
	 * pages to display.
	 * @param totalItems of int indicating the total items.
	 * @param pageSize of type int indicating the page size
	 * @return the total number of pages.
	 * @author Debadatta Mishra(PIKU)
	 */
	private static int getTotalPages( int totalItems , int pageSize )
	{
		int totalPages = totalItems/pageSize;
		if(totalItems % pageSize > 0)
		{
			totalPages++;
		}
		return totalPages;	
	}

	/**
	 * This method is used to obtain the list of data by
	 * passing the start index and the end index. Your
	 * data may come database by passing the start and the
	 * end index. For this purpose I made this method name as
	 * getDBDataList(). You can ask any database developer
	 * to fetch the data by passing the start and the end
	 * index.
	 * @param startIndex of type int indicating the start index.
	 * @param endIndex of type indicating the end index
	 * @return the list of data.
	 * @author Debadatta Mishra(PIKU)
	 */
	private static List getDBDataList( int startIndex , int endIndex )
	{
		List dataItemsList = new ArrayList();
		for(int i = startIndex+1 ; i < endIndex+1 ; i++ )
		{
			String data = "Test Item No "+i;
			dataItemsList.add("Test Item No "+i);
		}
		return dataItemsList;
	}

	/**
	 * This method is used to obtain the list of numbers for pagination.
	 * @param totalItems of type int indicating the total items.
	 * @param currentPage of type int indicating the current page.
	 * @param resultPageSize of type int indicating the result page size.
	 * @param pageGroupSize of type int indicating the page group size.
	 * @param defaultCursorPosition of type int indicating the default cursor position.
	 * @return the list of numbers for pagination
	 */
	private static List getList(int totalItems,
			int currentPage,
			int resultPageSize,
			int pageGroupSize,
			int defaultCursorPosition)
	{
		List pageList = new ArrayList();
		int finalPage = getTotalPages(totalItems, resultPageSize);
		int pageOffset = pageGroupSize;
		if(currentPage > defaultCursorPosition)
		{
			pageOffset -= defaultCursorPosition;
		}
		else
		{
			pageOffset -= (int)Math.min(pageGroupSize, currentPage);
		}
		int endPage = Math.min(finalPage, currentPage + pageOffset);
		
		int startPage = 0;
		if(currentPage > defaultCursorPosition && finalPage >= endPage)
		{
			startPage = Math.max(1,endPage - pageGroupSize + 1);
			pageList.add(0);
		}
		for( int i = startPage ;i<endPage;i++ )
		{
			pageList.add(i);
		}
		return pageList;
	}


}
