package com.cn.cloud.core.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * 
 * @ClassName   : SearchModel.java
 * @Description : 查询通用Model
 * @author Yin Xueyuan
 * @since 2017年2月23日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年2月23日        Yin Xueyuan           fisrt create
 * </pre>
 */
public class SearchModel implements Serializable {

	private static final long serialVersionUID = 7705757860844320645L;
	
    private int pageIndex = 1;
        
    private int pageSize = 10;

    private int firstIndex = -1;

    private int lastIndex = -1;

    private int recordCountPerPage = 10;
    
    private List<SortDescriptor> sort = null;
    
    private String sField = "";
    
    private String sText = "";
    
    private String sLangCd = "";

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	public List<SortDescriptor> getSort() {
		return sort;
	}

	public void setSort(List<SortDescriptor> sort) {
		if(sort != null && sort.size() == 0){
			sort = null;
		}		
		this.sort = sort;
	}

	public String getsField() {
		return sField;
	}

	public void setsField(String sField) {
		this.sField = sField;
	}

	public String getsText() {
		return sText;
	}

	public void setsText(String sText) {
		this.sText = sText;
	}

	public String getsLangCd() {
		return sLangCd;
	}

	public void setsLangCd(String sLangCd) {
		this.sLangCd = sLangCd;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstIndex;
		result = prime * result + lastIndex;
		result = prime * result + pageIndex;
		result = prime * result + pageSize;
		result = prime * result + recordCountPerPage;
		result = prime * result + ((sField == null) ? 0 : sField.hashCode());
		result = prime * result + ((sLangCd == null) ? 0 : sLangCd.hashCode());
		result = prime * result + ((sText == null) ? 0 : sText.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		return result;
	}

	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchModel other = (SearchModel) obj;
		if (firstIndex != other.firstIndex)
			return false;
		if (lastIndex != other.lastIndex)
			return false;
		if (pageIndex != other.pageIndex)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (recordCountPerPage != other.recordCountPerPage)
			return false;
		if (sField == null) {
			if (other.sField != null)
				return false;
		} else if (!sField.equals(other.sField))
			return false;
		if (sLangCd == null) {
			if (other.sLangCd != null)
				return false;
		} else if (!sLangCd.equals(other.sLangCd))
			return false;
		if (sText == null) {
			if (other.sText != null)
				return false;
		} else if (!sText.equals(other.sText))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		return true;
	}




	public static class SortDescriptor {
		
        

		private String field;
        private String dir;
        
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
        
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((dir == null) ? 0 : dir.hashCode());
			result = prime * result + ((field == null) ? 0 : field.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SortDescriptor other = (SortDescriptor) obj;
			if (dir == null) {
				if (other.dir != null)
					return false;
			} else if (!dir.equals(other.dir))
				return false;
			if (field == null) {
				if (other.field != null)
					return false;
			} else if (!field.equals(other.field))
				return false;
			return true;
		}
		
    }
}
