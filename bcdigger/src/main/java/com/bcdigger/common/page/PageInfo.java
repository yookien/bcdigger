package com.bcdigger.common.page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PageInfo<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    
 // 计算
 	private int pageCount; // 总页数
 	private int beginPageIndex; // 页码列表的开始索引（包含）
 	private int endPageIndex; // 页码列表的结束索引（包含）
 	
 	private Map<String, T> countResultMap; // 当前分页条件下的统计结果


    public PageInfo() {
    }
    
    /**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param total
	 * @param list
	 */
	public PageInfo(int pageNum, int pageSize, int total, List<T> list) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.list = list;

		// 计算总页码
		pageCount = (total + pageSize - 1) / pageSize;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = pageNum - 4;
			endPageIndex = pageNum + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}
	
	/**
	 * 只接受前5个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param total
	 * @param list
	 */
	public PageInfo(int pageNum, int pageSize, int total, List<T> list, Map<String, T> countResultMap) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.list = list;
		this.countResultMap = countResultMap;

		// 计算总页码
		pageCount = (total + pageSize - 1) / pageSize;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = pageNum - 4;
			endPageIndex = pageNum + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}


    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.list = page;
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = 1;
            this.list = list;
            this.total = list.size();
        }
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }

}
