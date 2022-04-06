<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="城市id" prop="cityId">
        <el-input
          v-model="queryParams.cityId"
          placeholder="请输入城市id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车场id" prop="lotId">
        <el-input
          v-model="queryParams.lotId"
          placeholder="请输入车场id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="泊位id" prop="placeId">
        <el-input
          v-model="queryParams.placeId"
          placeholder="请输入泊位id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆id" prop="carId">
        <el-input
          v-model="queryParams.carId"
          placeholder="请输入车辆id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="驶入时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择驶入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="驶出时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择驶出时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="停车时长" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入停车时长"
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
          v-hasPermi="['order:order:add']"
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
          v-hasPermi="['order:order:edit']"
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
          v-hasPermi="['order:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单id" align="center" prop="orderId" />
      <el-table-column label="城市id" align="center" prop="cityId" />
      <el-table-column label="车场名称" align="center" prop="params.lotName" />
      <el-table-column label="泊位id" align="center" prop="placeId" />
      <el-table-column label="车辆id" align="center" prop="carId" />
      <el-table-column label="驶入时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="驶出时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="停车时长" align="center" prop="duration" />
      <el-table-column label="删除标志" align="center" prop="orderStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:order:remove']"
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="城市id" prop="cityId">
          <el-input v-model="form.cityId" placeholder="请输入城市id" />
        </el-form-item>
        <el-form-item label="车场id" prop="lotId">
          <el-input v-model="form.lotId" placeholder="请输入车场id" />
        </el-form-item>

        <el-form-item label="车场">
                    	<el-select v-model="form.lotId" placeholder="请选择新车场">
                    		<el-option :label="lots.lotName" :value="lots.lotId" v-for="lots in lotList" :key="lots.lotId"></el-option>
                    	</el-select>
                    </el-form-item>

        <el-form-item label="泊位id" prop="placeId">
          <el-input v-model="form.placeId" placeholder="请输入泊位id" />
        </el-form-item>
        <el-form-item label="车辆id" prop="carId">
          <el-input v-model="form.carId" placeholder="请输入车辆id" />
        </el-form-item>
        <el-form-item label="驶入时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择驶入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="驶出时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择驶出时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="停车时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入停车时长" />
        </el-form-item>
        <el-divider content-position="center">车场信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <!-- <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddParkingLot">添加</el-button> -->
          </el-col>
          <el-col :span="1.5">
            <!-- <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteParkingLot">删除</el-button> -->
          </el-col>
        </el-row>
        <el-table :data="parkingLotList" :row-class-name="rowParkingLotIndex" @selection-change="handleParkingLotSelectionChange" ref="parkingLot">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="车场名称" prop="lotName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.lotName" placeholder="请输入车场名称" />
            </template>
          </el-table-column>
          <el-table-column label="车场简称" prop="shortName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.shortName" placeholder="请输入车场简称" />
            </template>
          </el-table-column>
          <el-table-column label="城区id" prop="cityId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cityId" placeholder="请输入城区id" />
            </template>
          </el-table-column>
          <el-table-column label="详细地址" prop="address" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.address" placeholder="请输入详细地址" />
            </template>
          </el-table-column>
          <el-table-column label="泊位数量" prop="placeNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.placeNum" placeholder="请输入泊位数量" />
            </template>
          </el-table-column>
          <el-table-column label="车场类型" prop="lotStyle" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.lotStyle" placeholder="请输入车场类型" />
            </template>
          </el-table-column>
          <el-table-column label="显示顺序" prop="orderNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.orderNum" placeholder="请输入显示顺序" />
            </template>
          </el-table-column>
          <el-table-column label="建成时间" prop="buildTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.buildTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择建成时间" />
            </template>
          </el-table-column>
          <el-table-column label="车场状态" prop="status" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.status" placeholder="请选择车场状态">
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/order/order";
import { listLot } from "@/api/system/lot";

export default {
  name: "Order",
  data() {
    return {
      lotName:undefined,
      lotList:{
        lotName:"",
        lotId: ""
      },
      lotList:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedParkingLot: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单管理表格数据
      orderList: [],
      // 车场表格数据
      parkingLotList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cityId: null,
        lotId: null,
        placeId: null,
        carId: null,
        startTime: null,
        endTime: null,
        duration: null,
        orderStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getParkingLotList();
    this.getList();
  },
  methods: {
    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        console.log(this.orderList);
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 车场初始化 */
        getParkingLotList() {
          listLot().then(response => {
           this.lotList = response.rows;
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
        orderId: null,
        cityId: null,
        lotId: null,
        placeId: null,
        carId: null,
        startTime: null,
        endTime: null,
        duration: null,
        orderStatus: "0",
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.parkingLotList = [];
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
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.parkingLotList = response.data.parkingLotList;
        this.open = true;
        this.title = "修改订单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.parkingLotList = this.parkingLotList;
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除订单管理编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 车场序号 */
    rowParkingLotIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 车场添加按钮操作 */
    handleAddParkingLot() {
      let obj = {};
      obj.lotName = "";
      obj.shortName = "";
      obj.cityId = "";
      obj.address = "";
      obj.placeNum = "";
      obj.lotStyle = "";
      obj.orderNum = "";
      obj.buildTime = "";
      obj.status = "";
      this.parkingLotList.push(obj);
    },
    /** 车场删除按钮操作 */
    handleDeleteParkingLot() {
      if (this.checkedParkingLot.length == 0) {
        this.$modal.msgError("请先选择要删除的车场数据");
      } else {
        const parkingLotList = this.parkingLotList;
        const checkedParkingLot = this.checkedParkingLot;
        this.parkingLotList = parkingLotList.filter(function(item) {
          return checkedParkingLot.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleParkingLotSelectionChange(selection) {
      this.checkedParkingLot = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
