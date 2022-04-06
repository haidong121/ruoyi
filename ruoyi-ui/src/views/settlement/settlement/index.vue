<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="结算方名称" prop="settlementName">
        <el-input
          v-model="queryParams.settlementName"
          placeholder="请输入结算方名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属城区" prop="cityId">
        <el-input
          v-model="queryParams.cityId"
          placeholder="请输入所属城区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结算账期" prop="settlementPeriod">
        <el-input
          v-model="queryParams.settlementPeriod"
          placeholder="请输入结算账期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="营业执照编号" prop="businessLicense">
        <el-input
          v-model="queryParams.businessLicense"
          placeholder="请输入营业执照编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="银行开户名" prop="accountName">
        <el-input
          v-model="queryParams.accountName"
          placeholder="请输入银行开户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开户行" prop="accountBank">
        <el-input
          v-model="queryParams.accountBank"
          placeholder="请输入开户行"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="leader">
        <el-input
          v-model="queryParams.leader"
          placeholder="请输入负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['settlement:settlement:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['settlement:settlement:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['settlement:settlement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['settlement:settlement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="settlementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="结算方编号" align="center" prop="settlementId" />
      <el-table-column label="结算方名称" align="center" prop="settlementName" />
      <el-table-column label="所属城区" align="center" prop="cityId" />
      <el-table-column label="结算账期" align="center" prop="settlementPeriod" />
      <el-table-column label="营业执照编号" align="center" prop="businessLicense" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="银行开户名" align="center" prop="accountName" />
      <el-table-column label="开户行" align="center" prop="accountBank" />
      <el-table-column label="负责人" align="center" prop="leader" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['settlement:settlement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['settlement:settlement:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改结算管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="结算方名称" prop="settlementName">
          <el-input v-model="form.settlementName" placeholder="请输入结算方名称" />
        </el-form-item>
        <el-form-item label="所属城区" prop="cityId">
          <el-input v-model="form.cityId" placeholder="请输入所属城区" />
        </el-form-item>
        <el-form-item label="结算账期" prop="settlementPeriod">
          <el-input v-model="form.settlementPeriod" placeholder="请输入结算账期" />
        </el-form-item>
        <el-form-item label="营业执照编号" prop="businessLicense">
          <el-input v-model="form.businessLicense" placeholder="请输入营业执照编号" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="银行开户名" prop="accountName">
          <el-input v-model="form.accountName" placeholder="请输入银行开户名" />
        </el-form-item>
        <el-form-item label="开户行" prop="accountBank">
          <el-input v-model="form.accountBank" placeholder="请输入开户行" />
        </el-form-item>
        <el-form-item label="负责人" prop="leader">
          <el-input v-model="form.leader" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-divider content-position="center">订单管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddOrder">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteOrder">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="orderList" :row-class-name="rowOrderIndex" @selection-change="handleOrderSelectionChange" ref="order">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="城市id" prop="cityId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cityId" placeholder="请输入城市id" />
            </template>
          </el-table-column>
          <el-table-column label="车场id" prop="lotId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.lotId" placeholder="请输入车场id" />
            </template>
          </el-table-column>
          <el-table-column label="泊位id" prop="placeId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.placeId" placeholder="请输入泊位id" />
            </template>
          </el-table-column>
          <el-table-column label="车辆id" prop="carId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.carId" placeholder="请输入车辆id" />
            </template>
          </el-table-column>
          <el-table-column label="驶入时间" prop="startTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.startTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择驶入时间" />
            </template>
          </el-table-column>
          <el-table-column label="驶出时间" prop="endTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.endTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择驶出时间" />
            </template>
          </el-table-column>
          <el-table-column label="停车时长" prop="duration" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.duration" placeholder="请输入停车时长" />
            </template>
          </el-table-column>
          <el-table-column label="删除标志" prop="orderStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.orderStatus" placeholder="请选择删除标志">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSettlement, getSettlement, delSettlement, addSettlement, updateSettlement } from "@/api/settlement/settlement";

export default {
  name: "Settlement",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedOrder: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 结算管理表格数据
      settlementList: [],
      // 订单管理表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        settlementName: null,
        cityId: null,
        settlementPeriod: null,
        businessLicense: null,
        address: null,
        accountName: null,
        accountBank: null,
        leader: null,
        phone: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询结算管理列表 */
    getList() {
      this.loading = true;
      listSettlement(this.queryParams).then(response => {
        this.settlementList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        settlementId: null,
        settlementName: null,
        cityId: null,
        settlementPeriod: null,
        businessLicense: null,
        address: null,
        accountName: null,
        accountBank: null,
        leader: null,
        phone: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.orderList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.settlementId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加结算管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const settlementId = row.settlementId || this.ids
      getSettlement(settlementId).then(response => {
        this.form = response.data;
        this.orderList = response.data.orderList;
        this.open = true;
        this.title = "修改结算管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.orderList = this.orderList;
          if (this.form.settlementId != null) {
            updateSettlement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSettlement(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const settlementIds = row.settlementId || this.ids;
      this.$modal.confirm('是否确认删除结算管理编号为"' + settlementIds + '"的数据项？').then(function() {
        return delSettlement(settlementIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 订单管理序号 */
    rowOrderIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 订单管理添加按钮操作 */
    handleAddOrder() {
      let obj = {};
      obj.cityId = "";
      obj.lotId = "";
      obj.placeId = "";
      obj.carId = "";
      obj.startTime = "";
      obj.endTime = "";
      obj.duration = "";
      obj.orderStatus = "";
      this.orderList.push(obj);
    },
    /** 订单管理删除按钮操作 */
    handleDeleteOrder() {
      if (this.checkedOrder.length == 0) {
        this.$modal.msgError("请先选择要删除的订单管理数据");
      } else {
        const orderList = this.orderList;
        const checkedOrder = this.checkedOrder;
        this.orderList = orderList.filter(function(item) {
          return checkedOrder.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleOrderSelectionChange(selection) {
      this.checkedOrder = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('settlement/settlement/export', {
        ...this.queryParams
      }, `settlement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
