package com.test.androidavp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.androidavp.BaseActivity
import com.test.androidavp.dto.RocketListDto
import com.test.androidavp.roomdatabase.entity.RocketEntity
import com.it.task.utility.Utility
import com.test.androidavp.MyApplication
import com.test.androidavp.databinding.ActivityMainBinding
import com.test.androidavp.ui.adapter.RocketAdapter
import com.test.androidavp.viewmodel.RocketViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: RocketViewModel
    var linearLayoutManager: LinearLayoutManager? = null
    private var rocketAdapter: RocketAdapter? = null
    private var rocketList = ArrayList<RocketListDto>()
    private var rocketEntityList = ArrayList<RocketEntity>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApplication.appComponent.inject(this)
        init()
    }

    private fun init() {
        setAdapter()
        if (!Utility(this).isNetworkAvailable) {
            viewModel.getRocketDao()
            viewModel.rocketDaoState.observe(this) {
                rocketEntityList.addAll(it.getAll())
                rocketAdapter?.notifyDataSetChanged()
                return@observe
            }
        } else {
            showProgressDialog()
            viewModel.callRocketList()
            viewModel.dtoState.observe(this) {
                hideProgressDialog()
                rocketList.addAll(it)
                rocketAdapter?.notifyDataSetChanged()

                if (it.isNotEmpty()) {
                    viewModel.getRocketDao()

                    viewModel.rocketDaoState.observe(this) { dao ->
                        val rocketList = dao.getAll()
                        if (rocketList.isNotEmpty()) {
                            for (i in it.indices) {
                                var isPresent = false
                                for (j in rocketList.indices) {
                                    if (it[i].id == rocketList[j].id) {
                                        isPresent = true
                                        break
                                    }
                                }
                                if(!isPresent){
                                    dao.insert(
                                        RocketEntity(
                                            0,
                                            id = it[i].id,
                                            name = it[i].name,
                                            image = it[i].flickrImages[0]!!,
                                            country = it[i].country!!,
                                            engineCount = it[i].firstStage?.engines!!,
                                            activeStatus = it[i].active!!,
                                            costPerLaunch = it[i].costPerLaunch!!,
                                            successRatePer = it[i].successRatePct!!,
                                            description = it[i].description!!,
                                            wikipedia = it[i].wikipedia!!,
                                            height = it[i].height?.feet!! * 12,
                                            diameter = it[i].diameter?.feet!! * 12,
                                            flickrImages = it[i].flickrImages as ArrayList<String>
                                        )
                                    )
                                }
                            }
                        } else {
                            for (i in it.indices) {
                                val rocketEntity = RocketEntity(
                                    0,
                                    id = it[i].id,
                                    name = it[i].name,
                                    image = it[i].flickrImages[0]!!,
                                    country = it[i].country!!,
                                    engineCount = it[i].firstStage?.engines!!,
                                    activeStatus = it[i].active!!,
                                    costPerLaunch = it[i].costPerLaunch!!,
                                    successRatePer = it[i].successRatePct!!,
                                    description = it[i].description!!,
                                    wikipedia = it[i].wikipedia!!,
                                    height = it[i].height?.feet!! * 12,
                                    diameter = it[i].diameter?.feet!! * 12,
                                    flickrImages = it[i].flickrImages as ArrayList<String>
                                )
                                dao.insert(rocketEntity)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvRocket.layoutManager = linearLayoutManager
        rocketAdapter = RocketAdapter(this, rocketList, rocketEntityList, object : RocketAdapter.RocketInterface {
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity, RocketDetailActivity::class.java)
                intent.putExtra("id", if(!rocketList.isEmpty()) rocketList[position].id else rocketEntityList[position].id)
                if(rocketList.isEmpty())
                    intent.putExtra("roomData", rocketEntityList[position])
                startActivity(intent)
            }

        })
        binding.rvRocket.adapter = rocketAdapter
    }
}