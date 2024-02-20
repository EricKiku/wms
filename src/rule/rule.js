function getMenu(ruleArr) {

    const result = []
    ruleArr.forEach(item => {
        menus.forEach(menu => {
            if (menu.mid === item) {
                result.push(menu)
            }
        })
    })
    return result
}


export const rule = (ruleStr) => {

}