const units: Record<number, string> = {
  6: "МЕТР",
  55: "КВАДРАТНЫЙ МЕТР",
  166: "КИЛОГРАММ",
  163: "ГРАММ",
  113: "КУБИЧЕСКИЙ МЕТР",
  112: "ЛИТР",
  797: "СТО ШТУК",
  715: "ПАРА",
  798: "ТЫСЯЧА ШТУК",
  796: "ШТУКА",
  306: "ГРАММ ДЕЛЯЩИХСЯ ИЗОТОПОВ",
  185: "ГРУЗОПОДЪЕМНОСТЬ В ТОННАХ",
  861: "КИЛОГРАММ АЗОТА",
  859: "КИЛОГРАММ ГИДРОКСИДА КАЛИЯ",
  863: "КИЛОГРАММ ГИДРОКСИДА НАТРИЯ",
  852: "КИЛОГРАММ ОКСИДА КАЛИЯ",
  841: "КИЛОГРАММ ПЕРОКСИДА ВОДОРОДА",
  865: "КИЛОГРАММ ПЯТИОКИСИ ФОСФОРА",
  845: "КИЛОГРАММ 90 %-ГО СУХОГО ВЕЩЕСТВА",
  867: "КИЛОГРАММ УРАНА",
  831: "ЛИТР ЧИСТОГО (100%) СПИРТА",
  162: "МЕТРИЧЕСКИЙ КАРАТ(1КАРАТ=2*10(-4)КГ",
  246: "1000 КИЛОВАТТ-ЧАС",
  305: "КЮРИ",
  111: "КУБИЧЕСКИЙ САНТИМЕТР",
  130: "1000 ЛИТРОВ",
  114: "1000 КУБИЧЕСКИХ МЕТРОВ",
  168: "ТОННА",
  251: "ЛОШАДИНАЯ СИЛА",
  214: "КИЛОВАТТ"
};

interface ProductInterface {
  innerId: string;
  unit: number;
}

function fetchProductUnits(products: ProductInterface[]): void {
  console.log(products)
  for (const product of products) {
    const unitName = units[product.unit];
    const unitParagraphNode = document.getElementById(product.innerId).getElementsByClassName("unit")[0]
    unitParagraphNode.innerHTML = unitName
  }
}
