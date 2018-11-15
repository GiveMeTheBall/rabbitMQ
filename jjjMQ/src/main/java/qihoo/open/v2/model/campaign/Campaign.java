package qihoo.open.v2.model.campaign;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import qihoo.open.common.core.ObjectUtils;
import qihoo.open.common.http.model.PostParameter;
import qihoo.open.common.model.BaseModel;
import qihoo.open.common.model.Failure;
import qihoo.open.common.model.type.CampaignStatusType;
import qihoo.open.common.util.CalendarUtil.DateFormateType;
import qihoo.open.common.util.ConvertUtil;
import qihoo.open.v2.model.campaign.Week;
import qihoo.open.v2.model.common.NegativeWords;


	/**
	 * 推广计划
	 * 
	 * @author ygzhang
	 * 
	 */
	public class Campaign extends BaseModel {

		/**
		 * 系列的远程Id
		 */
		private Long id;

		/**
		 * 推广计划名称
		 */
		private String name;

		/**
		 * 投放地域，json 格式的地域 id 数组，默认为全地域投放
		 */
		private String region;

		/**
		 * 推广计划开始日期，默认为添加之日开始
		 */
		private Date startDate;

		/**
		 * 推广计划结束日期，默认永久有效
		 */
		private Date endDate;
		private Date updateTime;
		private Date addTime;

		/**
		 * json 格式，Week 字段 1-7 表示周一至周日，若其中一 星期某天不投放，可跳过， hour 总数为 24 其中 0 为此时段不投放，1
		 * 为投放。 例:[{“week”:1, ”hour”:[1,1,1,1,1,1,1,1,1,1……]},{“week”:3,
		 * ”hour”:[1,0,1,1,1,0,1,1,1,1……]}……]每周 二不投放。
		 */
		private List<Week> schedule = new ArrayList<Week>();

		/**
		 * 推广计划每日预算，默认为 0，null表示不限制
		 */
		private Double budget;

		/**
		 * 推广计划在远程的状态
		 */
		private CampaignStatusType status;

		
		private List<Failure> failures;

		
		private String extendAdType;
		
		private String sysStatus;
		
		private Double mobilePriceRate;
		
		private NegativeWords negativeWords;

		public NegativeWords getNegativeWords() {
			return this.negativeWords;
		}

		public void setNegativeWords(NegativeWords negativeWords) {
			this.negativeWords = negativeWords;
		}
		public Double getMobilePriceRate() {
			return this.mobilePriceRate;
		}

		public void setMobilePriceRate(Double mobilePriceRate) {
			this.mobilePriceRate = mobilePriceRate;
		}
		
		public String getExtendAdType() {
			return extendAdType;
		}

		public void setExtendAdType(String extendAdType) {
			this.extendAdType = extendAdType;
		}

		public String getSysStatus() {
			return sysStatus;
		}

		public void setSysStatus(String sysStatus) {
			this.sysStatus = sysStatus;
		}

		/**
		 * 推广计划名称
		 */
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 投放地域，json 格式的地域 id 数组，默认为全地域投放
		 */
		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		/**
		 * 推广计划开始日期，默认为添加之日开始
		 */
		public Date getStartDate() {
			return startDate;
		}

		/**
		 * 推广计划结束日期，默认永久有效
		 */
		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		/**
		 * json 格式，Week 字段 1-7 表示周一至周日，若其中一 星期某天不投放，可跳过， hour 总数为 24 其中 0 为此时段不投放，1
		 * 为投放。 例:[{“week”:1, ”hour”:[1,1,1,1,1,1,1,1,1,1……]},{“week”:3,
		 * ”hour”:[1,0,1,1,1,0,1,1,1,1……]}……]每周 二不投放。
		 */
		public List<Week> getSchedule() {
			return schedule;
		}

		public void setSchedule(List<Week> schedule) {
			this.schedule = schedule;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		/**
		 * 推广计划每日预算，默认为 0，null表示不限制
		 */
		public Double getBudget() {
			return budget;
		}

		public void setBudget(Double budget) {
			this.budget = budget;
		}

		/**
		 * 推广计划在远程的状态
		 */
		public CampaignStatusType getStatus() {
			return status;
		}

		public void setStatus(CampaignStatusType status) {
			this.status = status;
		}

		/**
		 * 系列的远程Id
		 */
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return this.makeIdentifiableString(this, id, name, budget, status,
					updateTime, addTime, startDate, endDate, schedule, mobilePriceRate, failures);
		}

		@Override
		public void setFailures(List<Failure> failures) {
			this.failures = failures;
		}

		@Override
		public List<Failure> getFailures() {
			return failures;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public Date getAddTime() {
			return addTime;
		}

		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}

		@Override
		public List<PostParameter> getParameters() {
			if (id != null) {
				this.postParameters.add(new PostParameter("id", id));
			}
			if (name != null) {
				this.postParameters.add(new PostParameter("name", name));
			}
			if (region != null)
				this.postParameters.add(new PostParameter("region", region));
			if (startDate != null) {
				this.postParameters.add(new PostParameter("startDate", this
						.getDate(startDate, DateFormateType.DATE_FORMAT)));
			}
			if (endDate != null) {
				this.postParameters.add(new PostParameter("endDate", this
						.getDate(endDate, DateFormateType.DATE_FORMAT)));
			}
			if (updateTime != null) {
				this.postParameters.add(new PostParameter("updateTime", this
						.getDate(updateTime, DateFormateType.DATE_TIME_FORMAT)));
			}
			if (addTime != null) {
				this.postParameters.add(new PostParameter("addTime", this.getDate(
						addTime, DateFormateType.DATE_TIME_FORMAT)));
			}
			if (budget != null) {
				this.postParameters.add(new PostParameter("budget", budget.intValue()));
			}
			if (!ObjectUtils.isEmpty(this.schedule)) {
				this.postParameters.add(new PostParameter("schedule", ConvertUtil
						.listTojson(this.schedule)));
			}
			if (status != null) {
				this.postParameters.add(new PostParameter("status", this.status.getCode()));
			}
			return postParameters;
		}

		@Override
		protected Long getModelId() {
			return this.getId();
		}
	}

