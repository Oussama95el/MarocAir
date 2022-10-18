<%--
  Created by IntelliJ IDEA.
  User: abdessalm staili
  Date: 10/16/2022
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/Reservation" class="w-full max-w-lg">
  <div class="flex flex-wrap -mx-3 mb-6">
    <div class="w-full md:w-1/1 px-3 mb-6 md:mb-0">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-last-name">
        type of reservation
      </label>
      <select id="countries" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
        <option selected value="aller simple">Aller Simple</option>
        <option value="aller retour">Aller-retour</option>
      </select>
<%--this paragraphe if use don't choose your type the vol--%>
<%--<p class="text-red-500 text-xs italic">Please fill out this field.</p>--%>
    </div>
  </div>

  <div class="flex flex-wrap -mx-3 mb-2">
    <div class="w-full md:w-1/2 px-3">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-last-name">
        number adulte
      </label>
      <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="number" placeholder="? adulte">
    </div>
    <div class="w-full md:w-1/2 px-3">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-last-name">
        number enfants
      </label>
      <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"  type="number" placeholder="? enfants">
    </div>
  </div>
  <div class="flex flex-wrap justify-between -mx-3 mb-6">
    <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">
        lieu départ
      </label>
      <div class="relative">
        <select class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-state">
          <option>Texas</option>
        </select>
        <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
          <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
        </div>
      </div>
    </div>
    <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-state">
        lieu d’arrivé
      </label>
      <div class="relative">
        <select class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
          <option>New Mexico</option>
        </select>
        <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
          <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
        </div>
      </div>
    </div>
    <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-state">
        Date de d'epart
      </label>
      <div class="relative">
        <input type="date" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
      </div>
    </div>
  </div>
  <div class="w-full flex justify-center items-start md:w-1/1 px-3 mb-6 md:mb-0">
  <button class="bg-transparent w-2/5  hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded">
    Recherche
  </button>
  </div>
</form>
</body>
</html>
