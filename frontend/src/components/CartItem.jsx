import {useCart} from "../context/CartContext";

export default function CartItem({item}){
    const{dispatch} = useCart()
    return(
        <div className="rounded-xl border border-gray-200 bg-white p-4 shadow-sm">
            <div className="flex flex-col gap-4 sm:flex-row sm:items-center">
                <img src = {item.img} alt = {item.name} className="h-28 w-28 rounded-lg object-contain bg-gray-50 p-2"/>

                <div className="flex-1">
                    <div className="mb-2 flex items-start justify-between">
                         <div>
                            <h2 className="text-lg font-semibold text-gray-800">
                                {item.name}
                            </h2>
                            
                            
                        </div>

                        <i className = "bx bx-trash cursor-pointer text-xl text-red-500 transition hover:text-red-700"onClick ={()=> dispatch({type : "REMOVE_FROM_CART",payload: item.id})}></i>
                    </div>

                    <div >

                       <span >Description:</span>
                        <span >{item.description}</span>
                    </div>
                    <div>
                        <span>Size:</span>
                        <span >{item.size}</span>
                    </div>
                    <div>
                        <span>Color:</span>
                        < span >{item.color}</span>
                    </div>
                    <div>
                        <h1 >R{item.price}</h1>

                    </div>
                    <div className="flex items-center gap-3 rounded-lg border border-gray-300 px-3 py-2">
                        <i className = "bx bx-minus cursor-pointer text-lg text-gray-700 hover:text-black" onClick={()=>dispatch({type: "DECREMENT",payload : item.id })}></i>
                        <span >{item.quantity}</span>
                         <i className = "bx bx-plus cursor-pointer text-lg text-gray-700 hover:text-black" onClick={()=>dispatch({type: "INCREMENT",payload : item.id })}></i>
                    </div>
                </div>
            </div>
        </div>
    );
}