import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.R

class CartAdapter(private val cartItems: List<CartItem>,private val listener: OnCartItemClickListener) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface OnCartItemClickListener {
        fun onItemSub(item: CartItem, position: Int)
        fun onItemAdd(item: CartItem, position: Int)
        fun onItemDelete(item: CartItem, position: Int)
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImg: ImageView = itemView.findViewById(R.id.cartDetailImg)
        val foodName: TextView = itemView.findViewById(R.id.cartText)
        val quantity: TextView = itemView.findViewById(R.id.itemCountTextView)
        val totalPrice: TextView = itemView.findViewById(R.id.totalPrice)
        val addItem: ImageView = itemView.findViewById(R.id.addImageView)
        val removeItem: ImageView = itemView.findViewById(R.id.minusImageView)
        val deleteItems: ImageView = itemView.findViewById(R.id.deleteItems)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_detail_items, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.foodImg.setImageResource(item.imageResId)
        holder.foodName.text = item.name
        holder.quantity.text = "${item.quantity}"
        holder.totalPrice.text = item.getFormatedTotalPrice()
        holder.addItem.setOnClickListener {
            listener.onItemAdd(item, position)
        }

        holder.removeItem.setOnClickListener {
            listener.onItemSub(item, position)
        }
        holder.deleteItems.setOnClickListener {
            listener.onItemDelete(item,position)
        }

    }

    override fun getItemCount(): Int = cartItems.size
}
