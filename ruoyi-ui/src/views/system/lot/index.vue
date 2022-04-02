<template>
  <div class="app-container">

    <el-row :gutter="20">
      <!--城区数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="cityName"
            placeholder="请输入城区名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="cityOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="车场名称" prop="lotName">
          <el-input
            v-model="queryParams.lotName"
            placeholder="请输入车场名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>



        <el-form-item label="泊位数量" prop="placeNum">
          <el-input
            v-model="queryParams.placeNum"
            placeholder="请输入泊位数量"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="车场类型" prop="lotStyle">
          <el-input
            v-model="queryParams.lotStyle"
            placeholder="请输入车场类型"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>

        <el-form-item label="建成时间" prop="buildTime">
          <el-date-picker clearable
            v-model="queryParams.buildTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择建成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车场状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择车场状态" clearable>
            <el-option
              v-for="dict in dict.type.status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
          v-hasPermi="['system:lot:add']"
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
          v-hasPermi="['system:lot:edit']"
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
          v-hasPermi="['system:lot:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:lot:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="lotList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车场编号" align="center" prop="lotId" />
<!--      <el-table-column label="车场名称" align="center" prop="lotName" >-->
<!--        <template slot-scope="scope">-->
<!--          <router-link :to="'/system/dict-data/index/' + scope.row.dictId" class="link-type">-->
<!--            <span>{{ scope.row.dictType }}</span>-->
<!--          </router-link>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="车场名称" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="'/system/lot-date/index/' + scope.row.lotId" class="link-type">
            <span>{{ scope.row.lotName }}</span>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column label="车场简称" align="center" prop="shortName" />
      <el-table-column label="所属城区" align="center" prop="city.cityName" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="泊位数量" align="center" prop="placeNum" />
      <el-table-column label="车场类型" align="center" prop="lotStyle">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.lot_style" :value="scope.row.lotStyle"/>
        </template>
      </el-table-column>
      <el-table-column label="显示顺序" align="center" prop="orderNum" />
      <el-table-column label="建成时间" align="center" prop="buildTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buildTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车场状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:lot:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:lot:remove']"
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
      </el-col>
    </el-row>

    <!-- 添加或修改车场管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车场名称" prop="lotName">
          <el-input v-model="form.lotName" placeholder="请输入车场名称" />
        </el-form-item>
        <el-form-item label="车场简称" prop="shortName">
          <el-input v-model="form.shortName" placeholder="请输入车场简称" />
        </el-form-item>

        <el-form-item label="归属城区" prop="cityId">
          <treeselect v-model="form.cityId" :options="cityOptions" :show-count="true" placeholder="请选择归属城区" />
        </el-form-item>


        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="泊位数量" prop="placeNum">
          <el-input v-model="form.placeNum" placeholder="请输入泊位数量" />
        </el-form-item>

        <el-form-item label="车场类型">
          <el-select v-model="form.lotStyle"  placeholder="请输入车场类型">
            <el-option
              v-for="dict in dict.type.lot_style"
              :key="dict.value"
              :label="dict.label"
              :value="Number(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="显示顺序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入显示顺序" />
        </el-form-item>
        <el-form-item label="建成时间" prop="buildTime">
          <el-date-picker clearable
            v-model="form.buildTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择建成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车场状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="删除标志">
          <el-select v-model="form.delFlag" placeholder="请输入删除标志">
            <el-option
              v-for="dict in dict.type.del_flag"
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
import { listLot, getLot, delLot, addLot, updateLot } from "@/api/system/lot";
import { treeselect } from "@/api/system/city";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Lot",
  dicts: ['sys_common_status','lot_style','status','del_flag'],
  components: { Treeselect },
  data() {
    return {
      // 城区名称
      cityName: undefined,
      // 城区树选项
      cityOptions: undefined,
      defaultProps: {
        children: "children",
        label: "label"
      },
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
      // 车场管理表格数据
      lotList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lotName: null,
        shortName: null,
        cityId: null,
        address: null,
        placeNum: null,
        lotStyle: null,
        orderNum: null,
        buildTime: null,
        status: null,
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
    this.getTreeselect();
  },
  watch: {
    // 根据名称筛选部门树
    cityName(val) {
      this.$refs.tree.filter(val);
    }
  },
  methods: {
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },

    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.cityId = data.id;
      this.handleQuery();
    },

    /** 查询部门下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.cityOptions = response.data;
      });
    },
    /** 查询车场管理列表 */
    getList() {
      this.loading = true;
      listLot(this.queryParams).then(response => {
        this.lotList = response.rows;
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
        lotId: null,
        lotName: null,
        shortName: null,
        cityId: null,
        address: null,
        placeNum: null,
        lotStyle: null,
        orderNum: null,
        buildTime: null,
        status: "0",
        delFlag: null,
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
      this.queryParams.cityId=null;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.lotId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车场管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const lotId = row.lotId || this.ids
      getLot(lotId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车场管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.lotId != null) {
            updateLot(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLot(this.form).then(response => {
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
      const lotIds = row.lotId || this.ids;
      this.$modal.confirm('是否确认删除车场管理编号为"' + lotIds + '"的数据项？').then(function() {
        return delLot(lotIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/lot/export', {
        ...this.queryParams
      }, `lot_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
