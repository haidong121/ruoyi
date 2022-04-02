<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="78px">
      <el-form-item label="所属车场" prop="lotId">
        <el-input
          v-model="queryParams.lotId"
          placeholder="请输入所属车场"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属区域" prop="areaId">
        <el-input
          v-model="queryParams.areaId"
          placeholder="请输入所属区域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备SN码" prop="equipmentSncode">
        <el-input
          v-model="queryParams.equipmentSncode"
          placeholder="请输入设备SN码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绑定时间" prop="bindDate">
        <el-date-picker clearable
          v-model="queryParams.bindDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择绑定时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="泊位类型" prop="placeType">
        <el-select v-model="queryParams.placeType" placeholder="请选择泊位类型" clearable>
          <el-option
            v-for="dict in dict.type.place_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="泊位属性" prop="placeProperty">
        <el-input
          v-model="queryParams.placeProperty"
          placeholder="请输入泊位属性"
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
          v-hasPermi="['system:place:add']"
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
          v-hasPermi="['system:place:edit']"
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
          v-hasPermi="['system:place:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:place:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="placeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车位编号" align="center" prop="placeId" />
      <el-table-column label="所属车场" align="center" prop="lot.lotName" />
      <el-table-column label="所属区域" align="center" prop="area.areaName" />
      <el-table-column label="设备类型" align="center" prop="equipmentType" />
      <el-table-column label="设备SN码" align="center" prop="equipmentSncode" />
      <el-table-column label="绑定时间" align="center" prop="bindDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bindDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="泊位类型" align="center" prop="placeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.place_type" :value="scope.row.placeType"/>
        </template>
      </el-table-column>
      <el-table-column label="泊位属性" align="center" prop="placeProperty">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.place_property" :value="scope.row.placeProperty"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:place:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:place:remove']"
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

    <!-- 添加或修改车位管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" >
        <el-form-item label="所属车场" >
          <el-select v-model="form.lotId" @change="change(form.lotId)" placeholder="请选择所属车场" >
            <el-option
              v-for="item in lotList"
              :key="item.lotId"
              :label="item.lotName"
              :value="item.lotId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="所属区域" >
          <el-select v-model="form.areaId"    placeholder="请选择所属区域" >
            <el-option
              v-for="item in areaList"
              :key="item.areaId"
              :label="item.areaName"
              :value="Number(item.areaId)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备SN码" prop="equipmentSncode">
          <el-input v-model="form.equipmentSncode" placeholder="请输入设备SN码" />
        </el-form-item>
        <el-form-item label="绑定时间" prop="bindDate">
          <el-date-picker
            clearable
            v-model="form.bindDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择绑定时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="泊位类型" prop="placeType">
             <el-select v-model="form.placeType" placeholder="请选择泊位类型">
               <el-option
                 v-for="dict in dict.type.place_type"
                 :key="dict.value"
                 :label="dict.label"
                 :value="dict.value"
               ></el-option>
             </el-select>
        </el-form-item>
        <el-form-item label="泊位属性" prop="placeProperty">
          <el-select v-model="form.placeProperty" placeholder="请选择泊位属性">
            <el-option
              v-for="dict in dict.type.place_property"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPlace, getPlace, delPlace, addPlace, updatePlace } from "@/api/system/place";
import { listLot } from "@/api/system/lot";
import { getAreaByLot } from "@/api/system/area";

export default {
  name: "Place",
  dicts: ['place_type','place_property'],
  data() {
    return {
      //车场列表
      lotList:[],
      //区域列表
      areaList:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车位管理表格数据
      placeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lotId: null,
        areaId: null,
        equipmentType: null,
        equipmentSncode: null,
        bindDate: null,
        placeType: null,
        placeProperty: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },

    };
  },
  created() {
    this.getList();
    this.getLotList();
  },
  watch: {
    lotId(){
      alert(1);
    }
  },
  methods: {
    //新增车场值发生改变
    change(val){
      /** 查询区域管理列表 */
      this.loading = true;
      getAreaByLot(val).then(response => {
        this.areaList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询车场管理列表 */
    getLotList() {
      this.loading = true;
      listLot(this.queryParams).then(response => {
        this.lotList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询车位管理列表 */
    getList() {
      this.loading = true;
      listPlace(this.queryParams).then(response => {
        this.placeList = response.rows;
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
        placeId: null,
        lotId: null,
        areaId: null,
        equipmentType: null,
        equipmentSncode: null,
        bindDate: null,
        placeType: null,
        placeProperty: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
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
      this.ids = selection.map(item => item.placeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车位管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const placeId = row.placeId || this.ids;
      const lotId = row.lotId;
      getAreaByLot(lotId).then(response => {
        this.areaList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      getPlace(placeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车位管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.placeId != null) {
            updatePlace(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlace(this.form).then(response => {
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
      const placeIds = row.placeId || this.ids;
      this.$modal.confirm('是否确认删除车位管理编号为"' + placeIds + '"的数据项？').then(function() {
        return delPlace(placeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/place/export', {
        ...this.queryParams
      }, `place_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
