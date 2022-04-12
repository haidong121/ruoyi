<template>
  <div class="app-container">
    <el-descriptions title="车场信息">
      <el-descriptions-item label="车场编号">{{this.lotList[0].lotId}}</el-descriptions-item>
      <el-descriptions-item label="车场名称">{{this.lotList[0].lotName}}</el-descriptions-item>
      <el-descriptions-item label="车场简称">{{this.lotList[0].shortName}}</el-descriptions-item>
      <el-descriptions-item label="所属城区">{{this.lotList[0].city.cityName}}</el-descriptions-item>
      <el-descriptions-item label="详细地址">{{this.lotList[0].address}}</el-descriptions-item>
      <el-descriptions-item label="泊位数量">{{this.lotList[0].placeNum}}</el-descriptions-item>
      <el-descriptions-item label="车场类型">{{this.lotList[0].lotName}}</el-descriptions-item>
      <el-descriptions-item label="建成时间">{{this.lotList[0].buildTime}}</el-descriptions-item>

      <el-descriptions-item label="备注">
        <el-tag size="small">学校</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
    </el-descriptions>
  </div>
</template>


<script>
  import { listLot, getLot, delLot, addLot, updateLot } from "@/api/system/lot";
  import { treeselect } from "@/api/system/city";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "date",
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
          lotId: null,
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
        },
        lotId: null
      };
    },
    created() {
      this.lotId=this.$route.params && this.$route.params.lotId;
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
        this.queryParams.lotId=this.lotId;
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
