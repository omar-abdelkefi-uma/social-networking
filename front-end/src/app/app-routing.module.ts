import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DefaultComponent } from './default/default.component';


const routes: Routes = [
  { path: '', redirectTo: 'user/login', pathMatch: 'full' },
  { path: 'user', loadChildren: () => import('./login/login.module').then(m => m.LoginModule) },
  {
    path: '', component: DefaultComponent
    ,

    children: [
      { path: 'basic-ui', loadChildren: () => import('./basic-ui/basic-ui.module').then(m => m.BasicUiModule) },
      { path: 'charts', loadChildren: () => import('./charts/charts.module').then(m => m.ChartsDemoModule) },
      { path: 'forms', loadChildren: () => import('./forms/form.module').then(m => m.FormModule) },
      { path: 'tables', loadChildren: () => import('./tables/tables.module').then(m => m.TablesModule) },
      { path: 'icons', loadChildren: () => import('./icons/icons.module').then(m => m.IconsModule) },
      { path: 'general-pages', loadChildren: () => import('./general-pages/general-pages.module').then(m => m.GeneralPagesModule) },
      { path: 'apps', loadChildren: () => import('./apps/apps.module').then(m => m.AppsModule) },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'company', loadChildren: () => import('./company-crud-example/company-crud-example.module').then(m => m.CompanyCrudExampleModule) },
    ]


  },

  { path: 'user-pages', loadChildren: () => import('./user-pages/user-pages.module').then(m => m.UserPagesModule) },
  { path: 'error-pages', loadChildren: () => import('./error-pages/error-pages.module').then(m => m.ErrorPagesModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
