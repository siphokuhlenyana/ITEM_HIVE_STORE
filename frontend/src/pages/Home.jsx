import {Products} from "../data/Product";
import ProductCard from "../components/ProductCard"

export default function Home(){
    const groupedProducts = Products.reduce((groups, product) => {
        const category = product.category;
        if (!groups[category]) {
            groups[category] = [];
        }
        groups[category].push(product);
        return groups;
    }, {});
     return (
        <div className="mx-auto max-w-7xl space-y-10 px-4 py-8">
            {Object.entries(groupedProducts).map(([category, items]) => (
                <div key={category}>
                    <h2 className="mb-4 text-2xl font-bold text-gray-900">
                        {category}
                    </h2>

                    <div className="flex gap-6 overflow-x-auto pb-2">
                        {items.map((item) => (
                            <div key={item.id} className="min-w-[280px] flex-shrink-0">
                                <ProductCard item={item} />
                            </div>
                        ))}
                    </div>
                </div>
            ))}
        </div>
    );
}
   /* return (
        
        <div className="flex flex-wrap gap-6 justify-center">
            {Products.map((item) => {
                return <ProductCard key = {item.id} item = {item}/>
            })}
            </div>
    )
}
    */