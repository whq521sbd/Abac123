package com.aotuo.vegetable.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.ClassicListAdapter;
import com.aotuo.vegetable.entity.ClassifyMessage;
import com.aotuo.vegetable.sqlite.ClassifyDBDao;

import java.util.ArrayList;
import java.util.List;

public class ClassicFragment extends Fragment {

	private String classicName, id;
	private ListView listView;
	private TextView noData;
	private List<ClassifyMessage> list = new ArrayList<ClassifyMessage>();
	private ClassicListAdapter adapter;
	private ClassifyDBDao dao;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.classic_goods_list, null);
		dao = new ClassifyDBDao(getActivity());
		classicName = getArguments().getString("typename");
		id = getArguments().getString("id");
		initUI(view);
		initData();
		return view;
	}

	private void initUI(View v) {
		// TODO Auto-generated method stub
		listView = (ListView) v.findViewById(R.id.listView);

		adapter = new ClassicListAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(iclassicClick != null){
					iclassicClick.click(list.get(arg2));
				}
			}
		});
		noData = (TextView) v.findViewById(R.id.noData);
	}

	private void initData() {
		// TODO Auto-generated method stub
		list.clear();
		List<ClassifyMessage> sfsf = dao.getGroupsClassify(id);
		if(sfsf != null && sfsf.size() > 0) {
			list.addAll(sfsf);
			adapter.notifyDataSetChanged();
			noData.setVisibility(View.GONE);
		}else {
			noData.setVisibility(View.VISIBLE);
		}
	}

	private IClassicClick iclassicClick;
	public void setIClassicClick(IClassicClick icc){
		iclassicClick = icc;
	}
	public interface IClassicClick{
		void click(ClassifyMessage ce);
	}
}
