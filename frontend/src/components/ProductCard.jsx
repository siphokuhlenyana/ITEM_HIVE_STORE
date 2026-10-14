import { useCart } from "../context/CartContext"

export default function ProductCard({item}){
    const {dispatch} = useCart()

    const handleAddToCart = ()=>{
        dispatch({
            type: "ADD_TO_CART",
            payload: item,

        })
    }
    return(
        
        <div className= "w-full rounded-xl bg-white py-4 shadow-md sm:w-72 xl:[h-20.75rem] xl:w-80">
            <div className="flex h-56 items-center justify-center py-3"><img className = "w-40 object-contain" src = {item.img} alt = {item.name}/></div>

             <div className = "mt-6 space-y-2 px-4">
                <h2 className = "text-2x1 font-bold">{item.name}</h2>
            </div>

             <div className = "flex justify-between text-sm">
                <span className = "text-gray-500">Description:</span>
                <span>{item.description}</span>
            </div>

            <div className = "flex justify-between text-sm">
                <span className = "text-gray-500">Size:</span>
                <span>{item.size}</span>
            </div>

            <div className = "flex justify-between text-sm">
                <span className = "text-gray-500">Color:</span>
                <span>{item.color}</span>
            </div>

            <div className = "flex justify-between text-sm font-medium">
                <span className = "text-gray-500">Price:</span>
                 <span>R{item.price}</span>
            </div>

            <button onClick = {handleAddToCart}className ="mt-5 w-full cursor-pointer rounded-se-2x1 bg-black p-3 text-white transition-colors duration-200 hover:bg-amber-800">Add To Cart</button>
            </div>
    )
}