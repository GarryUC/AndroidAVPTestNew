package com.test.androidavp.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.androidavp.BaseActivity
import com.test.androidavp.dto.RocketDetailDto
import com.test.androidavp.roomdatabase.entity.RocketEntity
import com.it.task.utility.Utility
import com.test.androidavp.MyApplication
import com.test.androidavp.R
import com.test.androidavp.databinding.ActivityRocketDetailBinding
import com.test.androidavp.ui.adapter.RocketDetailAdapter
import com.test.androidavp.viewmodel.RocketDetailViewModel
import javax.inject.Inject

class RocketDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: RocketDetailViewModel
    var linearLayoutManager: LinearLayoutManager? = null
    private var rocketDetailAdapter: RocketDetailAdapter? = null
    private var rocketList = ArrayList<String>()
    private lateinit var binding : ActivityRocketDetailBinding
    var id = ""
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApplication.appComponent.inject(this)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun init() {
        setAdapter()
        if(intent.getStringExtra("id")!=null) {
            id = intent.getStringExtra("id")!!

            if (!Utility(this).isNetworkAvailable) {
                if(intent.getParcelableExtra<RocketEntity>("roomData")!=null) {
                   val rocketEntityDto = intent.getParcelableExtra<RocketEntity>("roomData")
                    setRoomData(rocketEntityDto)
                }
                return
            }
            showProgressDialog()
            viewModel.callRocketDetail(id)

        }
        viewModel.rocketDetailState.observe(this) {
            hideProgressDialog()
            binding.tvError.visibility = View.GONE
            binding.llFullLayout.visibility = View.VISIBLE
            setData(it)
        }
        viewModel.failureState.observe(this) {
            hideProgressDialog()
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = it
            binding.llFullLayout.visibility = View.GONE
        }
    }

    private fun setRoomData(rocketEntityDto: RocketEntity?) {
        binding.llFullLayout.visibility = View.VISIBLE
        binding.tvName.text = rocketEntityDto?.name
        binding.tvActiveStatus.text = "${rocketEntityDto?.activeStatus}"
        binding.tvCostPerLaunch.text = "${rocketEntityDto?.costPerLaunch}"
        binding.tvSuccessRatePer.text = "${rocketEntityDto?.successRatePer}"
        binding.tvDescription.text = "${rocketEntityDto?.description}"
        binding.tvWikiLink.text = "${rocketEntityDto?.wikipedia}"
        binding.tvHeight.text = "${rocketEntityDto?.height}"
        binding.tvDiameter.text = "${rocketEntityDto?.diameter}"
        rocketEntityDto?.flickrImages?.let { rocketList.addAll(it) }
        rocketDetailAdapter?.notifyDataSetChanged()
    }

    private fun setData(rocketDetailDto: RocketDetailDto) {
        if (rocketDetailDto != null) {
            binding.tvName.text = rocketDetailDto.name
            binding.tvActiveStatus.text = "${rocketDetailDto.active}"
            binding.tvCostPerLaunch.text = "${rocketDetailDto.costPerLaunch}"
            binding.tvSuccessRatePer.text = "${rocketDetailDto.successRatePct}%"
            binding.tvDescription.text = "${rocketDetailDto.description}"
            binding.tvWikiLink.text = "${rocketDetailDto.wikipedia}"
            binding.tvHeight.text = "${(rocketDetailDto.height?.feet!! * 12).toInt()} ${getString(R.string.inches)}"
            binding.tvDiameter.text = "${(rocketDetailDto.diameter?.feet!! * 12).toInt()} ${getString(R.string.inches)}"
            rocketDetailDto.flickrImages?.let { rocketList.addAll(it) }
            rocketDetailAdapter?.notifyDataSetChanged()
        }
    }

    private fun setAdapter() {
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvRocketList.layoutManager = linearLayoutManager
        rocketDetailAdapter = RocketDetailAdapter(this, rocketList)
        binding.rvRocketList.adapter = rocketDetailAdapter

    }
}