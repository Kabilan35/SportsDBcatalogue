public class CellDecorator(private val spaceHeight: Int) :RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = spaceHeight
            if (parent.getChildAdapterPosition(view) == 0) {
                left = spaceHeight
            }
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}