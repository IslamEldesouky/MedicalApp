import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.R
import com.medicalapp.databinding.DrugItemBinding
import com.medicalapp.databinding.PrescriptionItemBinding
import com.medicalapp.domain.entity.MedicalDataResponse

class PrescriptionAdapter(private val itemSelected: ItemSelected) :
    ListAdapter<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName, PrescriptionAdapter.ViewHolder>(
        CategoryDiffCallback()
    ), DrugAdapter.ItemSelected {

    private var viewPool = RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            PrescriptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemSelected)
        val item = getItem(position)
        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(holder.rv.context, LinearLayoutManager.HORIZONTAL, false)
        linearLayoutManager.initialPrefetchItemCount = item.associatedDrug.size
        val adapter = DrugAdapter(this)
        adapter.submitList(item.associatedDrug)
        holder.rv.layoutManager = linearLayoutManager
        holder.rv.adapter = adapter
        holder.rv.setRecycledViewPool(viewPool)

    }

    class ViewHolder(private val itemBinding: PrescriptionItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        lateinit var rv: RecyclerView

        fun bind(
            prescriptionItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName,
            listener: ItemSelected
        ) {
            itemBinding.prescriptionItemTitle.text = "Prescription"
            rv = itemBinding.childRecyclerview
        }
    }

    class CategoryDiffCallback :
        DiffUtil.ItemCallback<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName>() {
        override fun areItemsTheSame(
            oldItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName,
            newItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName,
            newItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ItemSelected {
        fun itemSelected(prescriptionItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName)
        fun drugSelected(drugItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug)
    }

    override fun itemSelected(drugItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug) {
        itemSelected.drugSelected(drugItem)
    }
}