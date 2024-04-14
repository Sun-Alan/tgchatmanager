package top.tgchatmanager.entity;

import java.util.ArrayList;
import java.util.List;

public class GroupInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public GroupInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNull() {
            addCriterion("groupId is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("groupId is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(String value) {
            addCriterion("groupId =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(String value) {
            addCriterion("groupId <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(String value) {
            addCriterion("groupId >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(String value) {
            addCriterion("groupId >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(String value) {
            addCriterion("groupId <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(String value) {
            addCriterion("groupId <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLike(String value) {
            addCriterion("groupId like", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotLike(String value) {
            addCriterion("groupId not like", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<String> values) {
            addCriterion("groupId in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<String> values) {
            addCriterion("groupId not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(String value1, String value2) {
            addCriterion("groupId between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(String value1, String value2) {
            addCriterion("groupId not between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsIsNull() {
            addCriterion("ownerAndAnonymousAdmins is null");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsIsNotNull() {
            addCriterion("ownerAndAnonymousAdmins is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsEqualTo(String value) {
            addCriterion("ownerAndAnonymousAdmins =", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsNotEqualTo(String value) {
            addCriterion("ownerAndAnonymousAdmins <>", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsGreaterThan(String value) {
            addCriterion("ownerAndAnonymousAdmins >", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsGreaterThanOrEqualTo(String value) {
            addCriterion("ownerAndAnonymousAdmins >=", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsLessThan(String value) {
            addCriterion("ownerAndAnonymousAdmins <", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsLessThanOrEqualTo(String value) {
            addCriterion("ownerAndAnonymousAdmins <=", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsLike(String value) {
            addCriterion("ownerAndAnonymousAdmins like", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsNotLike(String value) {
            addCriterion("ownerAndAnonymousAdmins not like", value, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsIn(List<String> values) {
            addCriterion("ownerAndAnonymousAdmins in", values, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsNotIn(List<String> values) {
            addCriterion("ownerAndAnonymousAdmins not in", values, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsBetween(String value1, String value2) {
            addCriterion("ownerAndAnonymousAdmins between", value1, value2, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andOwnerandanonymousadminsNotBetween(String value1, String value2) {
            addCriterion("ownerAndAnonymousAdmins not between", value1, value2, "ownerandanonymousadmins");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNull() {
            addCriterion("groupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNotNull() {
            addCriterion("groupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupnameEqualTo(String value) {
            addCriterion("groupName =", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotEqualTo(String value) {
            addCriterion("groupName <>", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThan(String value) {
            addCriterion("groupName >", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("groupName >=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThan(String value) {
            addCriterion("groupName <", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThanOrEqualTo(String value) {
            addCriterion("groupName <=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLike(String value) {
            addCriterion("groupName like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotLike(String value) {
            addCriterion("groupName not like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameIn(List<String> values) {
            addCriterion("groupName in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotIn(List<String> values) {
            addCriterion("groupName not in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameBetween(String value1, String value2) {
            addCriterion("groupName between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotBetween(String value1, String value2) {
            addCriterion("groupName not between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingIsNull() {
            addCriterion("antiFloodSetting is null");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingIsNotNull() {
            addCriterion("antiFloodSetting is not null");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingEqualTo(String value) {
            addCriterion("antiFloodSetting =", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingNotEqualTo(String value) {
            addCriterion("antiFloodSetting <>", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingGreaterThan(String value) {
            addCriterion("antiFloodSetting >", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingGreaterThanOrEqualTo(String value) {
            addCriterion("antiFloodSetting >=", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingLessThan(String value) {
            addCriterion("antiFloodSetting <", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingLessThanOrEqualTo(String value) {
            addCriterion("antiFloodSetting <=", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingLike(String value) {
            addCriterion("antiFloodSetting like", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingNotLike(String value) {
            addCriterion("antiFloodSetting not like", value, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingIn(List<String> values) {
            addCriterion("antiFloodSetting in", values, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingNotIn(List<String> values) {
            addCriterion("antiFloodSetting not in", values, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingBetween(String value1, String value2) {
            addCriterion("antiFloodSetting between", value1, value2, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andAntifloodsettingNotBetween(String value1, String value2) {
            addCriterion("antiFloodSetting not between", value1, value2, "antifloodsetting");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeIsNull() {
            addCriterion("captchaMode is null");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeIsNotNull() {
            addCriterion("captchaMode is not null");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeEqualTo(String value) {
            addCriterion("captchaMode =", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeNotEqualTo(String value) {
            addCriterion("captchaMode <>", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeGreaterThan(String value) {
            addCriterion("captchaMode >", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeGreaterThanOrEqualTo(String value) {
            addCriterion("captchaMode >=", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeLessThan(String value) {
            addCriterion("captchaMode <", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeLessThanOrEqualTo(String value) {
            addCriterion("captchaMode <=", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeLike(String value) {
            addCriterion("captchaMode like", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeNotLike(String value) {
            addCriterion("captchaMode not like", value, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeIn(List<String> values) {
            addCriterion("captchaMode in", values, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeNotIn(List<String> values) {
            addCriterion("captchaMode not in", values, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeBetween(String value1, String value2) {
            addCriterion("captchaMode between", value1, value2, "captchamode");
            return (Criteria) this;
        }

        public Criteria andCaptchamodeNotBetween(String value1, String value2) {
            addCriterion("captchaMode not between", value1, value2, "captchamode");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagIsNull() {
            addCriterion("keyWordsFlag is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagIsNotNull() {
            addCriterion("keyWordsFlag is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagEqualTo(String value) {
            addCriterion("keyWordsFlag =", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagNotEqualTo(String value) {
            addCriterion("keyWordsFlag <>", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagGreaterThan(String value) {
            addCriterion("keyWordsFlag >", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagGreaterThanOrEqualTo(String value) {
            addCriterion("keyWordsFlag >=", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagLessThan(String value) {
            addCriterion("keyWordsFlag <", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagLessThanOrEqualTo(String value) {
            addCriterion("keyWordsFlag <=", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagLike(String value) {
            addCriterion("keyWordsFlag like", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagNotLike(String value) {
            addCriterion("keyWordsFlag not like", value, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagIn(List<String> values) {
            addCriterion("keyWordsFlag in", values, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagNotIn(List<String> values) {
            addCriterion("keyWordsFlag not in", values, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagBetween(String value1, String value2) {
            addCriterion("keyWordsFlag between", value1, value2, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andKeywordsflagNotBetween(String value1, String value2) {
            addCriterion("keyWordsFlag not between", value1, value2, "keywordsflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagIsNull() {
            addCriterion("deleteKeywordFlag is null");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagIsNotNull() {
            addCriterion("deleteKeywordFlag is not null");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagEqualTo(String value) {
            addCriterion("deleteKeywordFlag =", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagNotEqualTo(String value) {
            addCriterion("deleteKeywordFlag <>", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagGreaterThan(String value) {
            addCriterion("deleteKeywordFlag >", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagGreaterThanOrEqualTo(String value) {
            addCriterion("deleteKeywordFlag >=", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagLessThan(String value) {
            addCriterion("deleteKeywordFlag <", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagLessThanOrEqualTo(String value) {
            addCriterion("deleteKeywordFlag <=", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagLike(String value) {
            addCriterion("deleteKeywordFlag like", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagNotLike(String value) {
            addCriterion("deleteKeywordFlag not like", value, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagIn(List<String> values) {
            addCriterion("deleteKeywordFlag in", values, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagNotIn(List<String> values) {
            addCriterion("deleteKeywordFlag not in", values, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagBetween(String value1, String value2) {
            addCriterion("deleteKeywordFlag between", value1, value2, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andDeletekeywordflagNotBetween(String value1, String value2) {
            addCriterion("deleteKeywordFlag not between", value1, value2, "deletekeywordflag");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampIsNull() {
            addCriterion("settingTimeStamp is null");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampIsNotNull() {
            addCriterion("settingTimeStamp is not null");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampEqualTo(String value) {
            addCriterion("settingTimeStamp =", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampNotEqualTo(String value) {
            addCriterion("settingTimeStamp <>", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampGreaterThan(String value) {
            addCriterion("settingTimeStamp >", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampGreaterThanOrEqualTo(String value) {
            addCriterion("settingTimeStamp >=", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampLessThan(String value) {
            addCriterion("settingTimeStamp <", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampLessThanOrEqualTo(String value) {
            addCriterion("settingTimeStamp <=", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampLike(String value) {
            addCriterion("settingTimeStamp like", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampNotLike(String value) {
            addCriterion("settingTimeStamp not like", value, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampIn(List<String> values) {
            addCriterion("settingTimeStamp in", values, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampNotIn(List<String> values) {
            addCriterion("settingTimeStamp not in", values, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampBetween(String value1, String value2) {
            addCriterion("settingTimeStamp between", value1, value2, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andSettingtimestampNotBetween(String value1, String value2) {
            addCriterion("settingTimeStamp not between", value1, value2, "settingtimestamp");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagIsNull() {
            addCriterion("intoGroupCheckFlag is null");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagIsNotNull() {
            addCriterion("intoGroupCheckFlag is not null");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagEqualTo(String value) {
            addCriterion("intoGroupCheckFlag =", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagNotEqualTo(String value) {
            addCriterion("intoGroupCheckFlag <>", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagGreaterThan(String value) {
            addCriterion("intoGroupCheckFlag >", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagGreaterThanOrEqualTo(String value) {
            addCriterion("intoGroupCheckFlag >=", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagLessThan(String value) {
            addCriterion("intoGroupCheckFlag <", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagLessThanOrEqualTo(String value) {
            addCriterion("intoGroupCheckFlag <=", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagLike(String value) {
            addCriterion("intoGroupCheckFlag like", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagNotLike(String value) {
            addCriterion("intoGroupCheckFlag not like", value, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagIn(List<String> values) {
            addCriterion("intoGroupCheckFlag in", values, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagNotIn(List<String> values) {
            addCriterion("intoGroupCheckFlag not in", values, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagBetween(String value1, String value2) {
            addCriterion("intoGroupCheckFlag between", value1, value2, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupcheckflagNotBetween(String value1, String value2) {
            addCriterion("intoGroupCheckFlag not between", value1, value2, "intogroupcheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagIsNull() {
            addCriterion("intoGroupWelcomeFlag is null");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagIsNotNull() {
            addCriterion("intoGroupWelcomeFlag is not null");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagEqualTo(String value) {
            addCriterion("intoGroupWelcomeFlag =", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagNotEqualTo(String value) {
            addCriterion("intoGroupWelcomeFlag <>", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagGreaterThan(String value) {
            addCriterion("intoGroupWelcomeFlag >", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagGreaterThanOrEqualTo(String value) {
            addCriterion("intoGroupWelcomeFlag >=", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagLessThan(String value) {
            addCriterion("intoGroupWelcomeFlag <", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagLessThanOrEqualTo(String value) {
            addCriterion("intoGroupWelcomeFlag <=", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagLike(String value) {
            addCriterion("intoGroupWelcomeFlag like", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagNotLike(String value) {
            addCriterion("intoGroupWelcomeFlag not like", value, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagIn(List<String> values) {
            addCriterion("intoGroupWelcomeFlag in", values, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagNotIn(List<String> values) {
            addCriterion("intoGroupWelcomeFlag not in", values, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagBetween(String value1, String value2) {
            addCriterion("intoGroupWelcomeFlag between", value1, value2, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupwelcomeflagNotBetween(String value1, String value2) {
            addCriterion("intoGroupWelcomeFlag not between", value1, value2, "intogroupwelcomeflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagIsNull() {
            addCriterion("intoGroupUserNameCheckFlag is null");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagIsNotNull() {
            addCriterion("intoGroupUserNameCheckFlag is not null");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagEqualTo(String value) {
            addCriterion("intoGroupUserNameCheckFlag =", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagNotEqualTo(String value) {
            addCriterion("intoGroupUserNameCheckFlag <>", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagGreaterThan(String value) {
            addCriterion("intoGroupUserNameCheckFlag >", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagGreaterThanOrEqualTo(String value) {
            addCriterion("intoGroupUserNameCheckFlag >=", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagLessThan(String value) {
            addCriterion("intoGroupUserNameCheckFlag <", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagLessThanOrEqualTo(String value) {
            addCriterion("intoGroupUserNameCheckFlag <=", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagLike(String value) {
            addCriterion("intoGroupUserNameCheckFlag like", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagNotLike(String value) {
            addCriterion("intoGroupUserNameCheckFlag not like", value, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagIn(List<String> values) {
            addCriterion("intoGroupUserNameCheckFlag in", values, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagNotIn(List<String> values) {
            addCriterion("intoGroupUserNameCheckFlag not in", values, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagBetween(String value1, String value2) {
            addCriterion("intoGroupUserNameCheckFlag between", value1, value2, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andIntogroupusernamecheckflagNotBetween(String value1, String value2) {
            addCriterion("intoGroupUserNameCheckFlag not between", value1, value2, "intogroupusernamecheckflag");
            return (Criteria) this;
        }

        public Criteria andAiflagIsNull() {
            addCriterion("aiFlag is null");
            return (Criteria) this;
        }

        public Criteria andAiflagIsNotNull() {
            addCriterion("aiFlag is not null");
            return (Criteria) this;
        }

        public Criteria andAiflagEqualTo(String value) {
            addCriterion("aiFlag =", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagNotEqualTo(String value) {
            addCriterion("aiFlag <>", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagGreaterThan(String value) {
            addCriterion("aiFlag >", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagGreaterThanOrEqualTo(String value) {
            addCriterion("aiFlag >=", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagLessThan(String value) {
            addCriterion("aiFlag <", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagLessThanOrEqualTo(String value) {
            addCriterion("aiFlag <=", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagLike(String value) {
            addCriterion("aiFlag like", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagNotLike(String value) {
            addCriterion("aiFlag not like", value, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagIn(List<String> values) {
            addCriterion("aiFlag in", values, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagNotIn(List<String> values) {
            addCriterion("aiFlag not in", values, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagBetween(String value1, String value2) {
            addCriterion("aiFlag between", value1, value2, "aiflag");
            return (Criteria) this;
        }

        public Criteria andAiflagNotBetween(String value1, String value2) {
            addCriterion("aiFlag not between", value1, value2, "aiflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagIsNull() {
            addCriterion("crontabFlag is null");
            return (Criteria) this;
        }

        public Criteria andCrontabflagIsNotNull() {
            addCriterion("crontabFlag is not null");
            return (Criteria) this;
        }

        public Criteria andCrontabflagEqualTo(String value) {
            addCriterion("crontabFlag =", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagNotEqualTo(String value) {
            addCriterion("crontabFlag <>", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagGreaterThan(String value) {
            addCriterion("crontabFlag >", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagGreaterThanOrEqualTo(String value) {
            addCriterion("crontabFlag >=", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagLessThan(String value) {
            addCriterion("crontabFlag <", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagLessThanOrEqualTo(String value) {
            addCriterion("crontabFlag <=", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagLike(String value) {
            addCriterion("crontabFlag like", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagNotLike(String value) {
            addCriterion("crontabFlag not like", value, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagIn(List<String> values) {
            addCriterion("crontabFlag in", values, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagNotIn(List<String> values) {
            addCriterion("crontabFlag not in", values, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagBetween(String value1, String value2) {
            addCriterion("crontabFlag between", value1, value2, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andCrontabflagNotBetween(String value1, String value2) {
            addCriterion("crontabFlag not between", value1, value2, "crontabflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagIsNull() {
            addCriterion("nightModeFlag is null");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagIsNotNull() {
            addCriterion("nightModeFlag is not null");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagEqualTo(String value) {
            addCriterion("nightModeFlag =", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagNotEqualTo(String value) {
            addCriterion("nightModeFlag <>", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagGreaterThan(String value) {
            addCriterion("nightModeFlag >", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagGreaterThanOrEqualTo(String value) {
            addCriterion("nightModeFlag >=", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagLessThan(String value) {
            addCriterion("nightModeFlag <", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagLessThanOrEqualTo(String value) {
            addCriterion("nightModeFlag <=", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagLike(String value) {
            addCriterion("nightModeFlag like", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagNotLike(String value) {
            addCriterion("nightModeFlag not like", value, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagIn(List<String> values) {
            addCriterion("nightModeFlag in", values, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagNotIn(List<String> values) {
            addCriterion("nightModeFlag not in", values, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagBetween(String value1, String value2) {
            addCriterion("nightModeFlag between", value1, value2, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andNightmodeflagNotBetween(String value1, String value2) {
            addCriterion("nightModeFlag not between", value1, value2, "nightmodeflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagIsNull() {
            addCriterion("canSendMediaFlag is null");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagIsNotNull() {
            addCriterion("canSendMediaFlag is not null");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagEqualTo(String value) {
            addCriterion("canSendMediaFlag =", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagNotEqualTo(String value) {
            addCriterion("canSendMediaFlag <>", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagGreaterThan(String value) {
            addCriterion("canSendMediaFlag >", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagGreaterThanOrEqualTo(String value) {
            addCriterion("canSendMediaFlag >=", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagLessThan(String value) {
            addCriterion("canSendMediaFlag <", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagLessThanOrEqualTo(String value) {
            addCriterion("canSendMediaFlag <=", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagLike(String value) {
            addCriterion("canSendMediaFlag like", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagNotLike(String value) {
            addCriterion("canSendMediaFlag not like", value, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagIn(List<String> values) {
            addCriterion("canSendMediaFlag in", values, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagNotIn(List<String> values) {
            addCriterion("canSendMediaFlag not in", values, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagBetween(String value1, String value2) {
            addCriterion("canSendMediaFlag between", value1, value2, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andCansendmediaflagNotBetween(String value1, String value2) {
            addCriterion("canSendMediaFlag not between", value1, value2, "cansendmediaflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagIsNull() {
            addCriterion("clearInfoFlag is null");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagIsNotNull() {
            addCriterion("clearInfoFlag is not null");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagEqualTo(String value) {
            addCriterion("clearInfoFlag =", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagNotEqualTo(String value) {
            addCriterion("clearInfoFlag <>", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagGreaterThan(String value) {
            addCriterion("clearInfoFlag >", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagGreaterThanOrEqualTo(String value) {
            addCriterion("clearInfoFlag >=", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagLessThan(String value) {
            addCriterion("clearInfoFlag <", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagLessThanOrEqualTo(String value) {
            addCriterion("clearInfoFlag <=", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagLike(String value) {
            addCriterion("clearInfoFlag like", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagNotLike(String value) {
            addCriterion("clearInfoFlag not like", value, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagIn(List<String> values) {
            addCriterion("clearInfoFlag in", values, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagNotIn(List<String> values) {
            addCriterion("clearInfoFlag not in", values, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagBetween(String value1, String value2) {
            addCriterion("clearInfoFlag between", value1, value2, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andClearinfoflagNotBetween(String value1, String value2) {
            addCriterion("clearInfoFlag not between", value1, value2, "clearinfoflag");
            return (Criteria) this;
        }

        public Criteria andReportflagIsNull() {
            addCriterion("reportFlag is null");
            return (Criteria) this;
        }

        public Criteria andReportflagIsNotNull() {
            addCriterion("reportFlag is not null");
            return (Criteria) this;
        }

        public Criteria andReportflagEqualTo(String value) {
            addCriterion("reportFlag =", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagNotEqualTo(String value) {
            addCriterion("reportFlag <>", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagGreaterThan(String value) {
            addCriterion("reportFlag >", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagGreaterThanOrEqualTo(String value) {
            addCriterion("reportFlag >=", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagLessThan(String value) {
            addCriterion("reportFlag <", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagLessThanOrEqualTo(String value) {
            addCriterion("reportFlag <=", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagLike(String value) {
            addCriterion("reportFlag like", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagNotLike(String value) {
            addCriterion("reportFlag not like", value, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagIn(List<String> values) {
            addCriterion("reportFlag in", values, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagNotIn(List<String> values) {
            addCriterion("reportFlag not in", values, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagBetween(String value1, String value2) {
            addCriterion("reportFlag between", value1, value2, "reportflag");
            return (Criteria) this;
        }

        public Criteria andReportflagNotBetween(String value1, String value2) {
            addCriterion("reportFlag not between", value1, value2, "reportflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagIsNull() {
            addCriterion("antiFloodFlag is null");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagIsNotNull() {
            addCriterion("antiFloodFlag is not null");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagEqualTo(String value) {
            addCriterion("antiFloodFlag =", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagNotEqualTo(String value) {
            addCriterion("antiFloodFlag <>", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagGreaterThan(String value) {
            addCriterion("antiFloodFlag >", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagGreaterThanOrEqualTo(String value) {
            addCriterion("antiFloodFlag >=", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagLessThan(String value) {
            addCriterion("antiFloodFlag <", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagLessThanOrEqualTo(String value) {
            addCriterion("antiFloodFlag <=", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagLike(String value) {
            addCriterion("antiFloodFlag like", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagNotLike(String value) {
            addCriterion("antiFloodFlag not like", value, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagIn(List<String> values) {
            addCriterion("antiFloodFlag in", values, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagNotIn(List<String> values) {
            addCriterion("antiFloodFlag not in", values, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagBetween(String value1, String value2) {
            addCriterion("antiFloodFlag between", value1, value2, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andAntifloodflagNotBetween(String value1, String value2) {
            addCriterion("antiFloodFlag not between", value1, value2, "antifloodflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagIsNull() {
            addCriterion("channelSpamFlag is null");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagIsNotNull() {
            addCriterion("channelSpamFlag is not null");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagEqualTo(String value) {
            addCriterion("channelSpamFlag =", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagNotEqualTo(String value) {
            addCriterion("channelSpamFlag <>", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagGreaterThan(String value) {
            addCriterion("channelSpamFlag >", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagGreaterThanOrEqualTo(String value) {
            addCriterion("channelSpamFlag >=", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagLessThan(String value) {
            addCriterion("channelSpamFlag <", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagLessThanOrEqualTo(String value) {
            addCriterion("channelSpamFlag <=", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagLike(String value) {
            addCriterion("channelSpamFlag like", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagNotLike(String value) {
            addCriterion("channelSpamFlag not like", value, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagIn(List<String> values) {
            addCriterion("channelSpamFlag in", values, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagNotIn(List<String> values) {
            addCriterion("channelSpamFlag not in", values, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagBetween(String value1, String value2) {
            addCriterion("channelSpamFlag between", value1, value2, "channelspamflag");
            return (Criteria) this;
        }

        public Criteria andChannelspamflagNotBetween(String value1, String value2) {
            addCriterion("channelSpamFlag not between", value1, value2, "channelspamflag");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}