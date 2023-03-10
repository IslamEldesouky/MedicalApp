import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.databinding.DrugItemBinding
import com.medicalapp.domain.entity.MedicalDataResponse

class DrugAdapter(private val itemSelected: ItemSelected) :
    ListAdapter<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug, DrugAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            DrugItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),itemSelected)

    }

    class ViewHolder(private val itemBinding: DrugItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(drugItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug, listener: ItemSelected) {
            itemBinding.drugItemName.text = drugItem.name
            itemBinding.drugItemDose.text = drugItem.dose
            itemBinding.drugItemStrength.text = drugItem.strength
            itemBinding.drugContainer.setOnClickListener { listener.itemSelected(drugItem) }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug>() {
        override fun areItemsTheSame(
            oldItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug,
            newItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug,
            newItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface ItemSelected {
        fun itemSelected(searchItem: MedicalDataResponse.Problem.Diabetes.Medication.MedicationsClass.ClassName.AssociatedDrug)
    }
}